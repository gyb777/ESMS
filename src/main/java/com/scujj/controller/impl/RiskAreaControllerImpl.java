package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.controller.RiskAreaController;
import com.scujj.entity.Result;
import com.scujj.entity.RiskAreaEntity;
import com.scujj.service.RiskAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/root")
public class RiskAreaControllerImpl implements RiskAreaController {
    @Autowired
    RiskAreaService riskAreaService;

    @Override
    @GetMapping("/riskarea")
    public Result getRiskAreaList(@RequestParam("page") Integer page,
                                  @RequestParam("limit") Integer limit,
                                  @RequestParam(value = "province", required = false) String province,
                                  @RequestParam(value = "county", required = false) String county,
                                  @RequestParam(value = "city", required = false) String city,
                                  @RequestParam(value = "grade", required = false) Integer grade) {
        QueryWrapper<RiskAreaEntity> wrapper = new QueryWrapper<>();
        if (null != province) {
            wrapper.like("province", province);
        }
        if (null != county) {
            wrapper.like("county", county);
        }
        if (null != city) {
            wrapper.like("city", city);
        }
        if (null != grade) {
            wrapper.eq("grade", grade);
        }
        Page<RiskAreaEntity> page1 = new Page<>(page, limit);
        Page<RiskAreaEntity> page2 = riskAreaService.page(page1, wrapper);
        long total = page2.getTotal();
        long pages = page2.getPages();
        List<RiskAreaEntity> list = page2.getRecords();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pages", pages);
        hashMap.put("total", total);
        hashMap.put("riskareaList", list);
        return new Result(1, "成功", hashMap);
    }
}
