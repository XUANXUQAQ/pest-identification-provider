package com.rjgc.controller;

import com.rjgc.exceptions.ResBody;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imgFake")
public class ImageFakeController {

    @PostMapping
    @ApiOperation("假的图片上传接口，用于前端el-upload进行图片的计算而无需上传的情况")
    public ResBody<String> uploadImg() {
        return ResBody.success();
    }
}
