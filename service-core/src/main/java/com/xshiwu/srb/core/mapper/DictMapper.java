package com.xshiwu.srb.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xshiwu.srb.core.pojo.dto.ExcelDictDTO;
import com.xshiwu.srb.core.pojo.entity.Dict;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2021-04-10
 */
public interface DictMapper extends BaseMapper<Dict> {
    void insertBatch(List<ExcelDictDTO> list);
}
