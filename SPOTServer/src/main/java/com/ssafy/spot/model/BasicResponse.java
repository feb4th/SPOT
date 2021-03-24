package com.ssafy.spot.model;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;

public class BasicResponse {
//    @ApiModelProperty(value = "status", position = 1)
//    public HttpStatus status;
    @ApiModelProperty(value = "data", position = 2)
    public String message;
    @ApiModelProperty(value = "object", position = 3)
    public Object result;
}
