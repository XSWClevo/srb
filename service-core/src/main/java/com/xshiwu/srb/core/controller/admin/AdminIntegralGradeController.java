package com.xshiwu.srb.core.controller.admin;

import com.xshiwu.common.result.R;
import com.xshiwu.srb.core.pojo.entity.IntegralGrade;
import com.xshiwu.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>积分等级表<p/>
 *
 * @author xsw
 * @version 1.0
 * @date 2021/4/11 12:40
 */
@Api(tags = "积分等级管理")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    /**
     * 查看IntegralGrade表全部数据
     *
     * @return json数据
     */
    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public R listAll() {
        log.info("哈哈哈哈哈");
        log.warn("哈哈哈哈哈哈");
        log.error("哈哈哈哈哈");
        Map<String, Object> map = new HashMap<>();
        List<IntegralGrade> list = integralGradeService.list();
        map.put("list", list);
        return R.ok().data(map);
    }

    /**
     * 根据id逻辑删除数据
     *
     * @param id 数据id
     * @return 删除结果
     */
    @ApiOperation(value = "根据id删除数据记录", notes = "逻辑删除数据")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value = "数据id", example = "100", required = true)
            @PathVariable Long id) {
        boolean flag = integralGradeService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功！");
        } else {
            return R.error().message("删除失败！");
        }
    }

    /**
     * 新增积分等级
     * @param integralGrade 积分表实体类
     * @return 是否成功
     */
    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade) {
        boolean result = integralGradeService.save(integralGrade);
        if (result){
            return R.ok().message("数据添加成功！");
        } else {
            return R.error().message("数据添加失败！");
        }
    }

    /**
     * 根据id获取积分等级
     * @param id 积分id
     * @return json数据
     */
    @ApiOperation("根据id获取积分等级")
    @PostMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id", example = "1", required = true)
            @PathVariable Long id){
        IntegralGrade integralGrade = integralGradeService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("record", integralGrade);
        if (integralGrade != null){
            return R.ok().data(map);
        } else {
            return R.error().message("数据不存在！");
        }
    }

    /**
     * 更新积分等级
     * @param integralGrade 更新的实体类
     * @return json字符
     */
    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean updateById = integralGradeService.updateById(integralGrade);
        if (updateById){
            return R.ok().message("数据更新成功！");
        } else {
            log.error("数据更新失败");
            return R.error().message("数据更新失败！");
        }
    }

}
