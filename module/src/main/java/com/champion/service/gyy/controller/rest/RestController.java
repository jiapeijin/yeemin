package com.champion.service.gyy.controller.rest;

import com.champion.framework.metadata.AjaxModel;
import com.champion.service.gyy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: restful风格接口
 * @Auther： william
 * @Date：2017/6/8 0008 14:48
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "model")
    public AjaxModel ajaxModel(ModelMap mm) {
        return new AjaxModel(true, testService.userList());
    }
}
