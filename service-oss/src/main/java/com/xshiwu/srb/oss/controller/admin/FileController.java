package com.xshiwu.srb.oss.controller.admin;

import com.xshiwu.common.exception.BusinessException;
import com.xshiwu.common.result.R;
import com.xshiwu.common.result.ResponseEnum;
import com.xshiwu.srb.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xsw
 * @version 1.0
 * @date 2021/4/17 18:51
 */
@Api(tags = "阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/api/oss/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file   上传的文件
     * @param module 上传的模块
     * @return R
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R upload(@ApiParam(value = "文件", required = true)
                    @RequestParam("file") MultipartFile file,
                    @ApiParam(value = "模块", required = true)
                    @RequestParam("module") String module) {
        try {
            InputStream is = file.getInputStream();
            String filename = file.getOriginalFilename();
            String url = fileService.upload(is, module, filename);
            return R.ok().message("文件上传成功").data("url", url);
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }

    @ApiOperation("删除OSS文件")
    @DeleteMapping("/remove")
    // @RequestParam用来处理 Content-Type 为 application/x-www-form-urlencoded 编码的内容，Content-Type默认为该属性。
    // @RequestParam也可用于其它类型的请求，例如：POST、DELETE等请求。
    public R deleteFile(@ApiParam(value = "要删除的文件目录", required = true)
                        @RequestParam("url") String url) {
        fileService.remove(url);
        return R.ok().message("删除成功");
    }
}
