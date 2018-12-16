package com.cunjunwang.hospital.emp.controller;

import com.cunjunwang.hospital.emp.entity.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * Created by CunjunWang on 2018/11/25.
 */
@RestController
@RequestMapping("/base")
@Api(value = "[Test API]测试API")
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "[测试]测试", notes = "[测试API]测试API")
    public ResultData<String> test(@ApiParam(name = "name", value = "测试参数", required = false) @RequestParam String name) {
        return new ResultData<>(ResultData.SUCCESS, "", "测试接口调用成功", name);
    }

}
