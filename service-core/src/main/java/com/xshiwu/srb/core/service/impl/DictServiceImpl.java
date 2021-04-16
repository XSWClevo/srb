package com.xshiwu.srb.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xshiwu.srb.core.listener.ExcelDictDTOListener;
import com.xshiwu.srb.core.mapper.DictMapper;
import com.xshiwu.srb.core.pojo.dto.ExcelDictDTO;
import com.xshiwu.srb.core.pojo.entity.Dict;
import com.xshiwu.srb.core.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author xsw
 * @since 2021-04-10
 */
@Service
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private RedisTemplate redisTemplate;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void importData(InputStream inputStream) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, ExcelDictDTO.class, new ExcelDictDTOListener(baseMapper)).sheet().doRead();
        log.info("importData finished");
    }

    /**
     * @return 批量导出
     */
    @Override
    public List<ExcelDictDTO> listDictData() {
        List<Dict> dicts = baseMapper.selectList(null);
        //创建ExcelDictDTO列表，将Dict列表转换成ExcelDictDTO列表
        ArrayList<ExcelDictDTO> excelDictDTOList = new ArrayList<>(dicts.size());

        dicts.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict, excelDictDTO);
            excelDictDTOList.add(excelDictDTO);
        });
        return excelDictDTOList;
    }

    /**
     * 判断该节点是否有子节点
     *
     * @param id Dict实体类的id
     */
    @Override
    public List<Dict> listByParentId(Long id) {

        try {
            // 1、查询redis中是否存在数据列表
            List<Dict> dictList = (List<Dict>) redisTemplate.opsForValue().get("srb:core:dictList:" + id);
            if (dictList != null) {
                // 2、如果存在直接从redis中返回数据列表
                return dictList;
            }
        } catch (Exception e) {
            log.error("redis服务器异常：{}", ExceptionUtils.getStackTrace(e));
        }
        // 3、如果不存在则查询数据库
        // select * from dict where parent_id = parentId
        log.info("从数据库中获取列表！");
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>().eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(queryWrapper);
        dictList.forEach(dict -> {
            // 根据自己id查询出的row数据，如果下面还有数据则继续往下面递归
            // == 如果有子节点，则是非叶子节点
            boolean hasChildren = hasChildrenDict(dict.getId());
            // 传入自定义的是否为叶子节点的字段
            dict.setHasChildren(hasChildren);
        });
        try {
            log.info("将数据存入redis");
            // 将数据存入redis
            redisTemplate.opsForValue().set("srb:core:dictList:" + id, dictList, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("redis服务器异常：{}", ExceptionUtils.getStackTrace(e));
        }
        return dictList;
    }


    /**
     * 递归查找判断该节点是否有子节点
     *
     * @param parentId 子id
     * @return Dict实体类自定义的hasChildren字段
     */
    private boolean hasChildrenDict(Long parentId) {
        // 这里继续递归查找
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>().eq("parent_id", parentId);
        Integer count = baseMapper.selectCount(queryWrapper);
        // return count > 0 ? true : false;
        return count > 0;
    }


}
