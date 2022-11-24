package com.scujj.controller;

import com.scujj.entity.Result;

public interface RiskAreaController {
    Result getRiskAreaList(Integer page,
                           Integer limit,
                           String province,
                           String county,
                           String city,
                           Integer grade);
}
