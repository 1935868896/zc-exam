package com.zc.modules.system.controller;

import com.zc.entity.ResultResponse;
import com.zc.modules.system.entity.SysMenu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangC
 * @create 2021-08-30-14:04
 */
@RestController
@RequestMapping("sysMenu")
public class SysMenuController {
    @GetMapping
    public ResultResponse menu(){
        List<SysMenu> list=new ArrayList<>();
//        list.add(sysMenu);
        return ResultResponse.success(list);
    }

}
