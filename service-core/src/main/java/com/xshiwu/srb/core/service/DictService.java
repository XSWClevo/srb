package com.xshiwu.srb.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xshiwu.srb.core.pojo.dto.ExcelDictDTO;
import com.xshiwu.srb.core.pojo.entity.Dict;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author xsw
 * @since 2021-04-10
 */
public interface DictService extends IService<Dict> {
    void importData(InputStream inputStream);
    List<ExcelDictDTO> listDictData();

    List<Dict> listByParentId(Long parentId);

}
