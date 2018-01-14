package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

public class Ajax {

    public static Map<String, Object> successResponse (Object o){
        Map<String, Object> response = new HashMap<>();
        response.put("result", "success");
        response.put("data", o);
        return response;
    }

    public static Map<String, Object> emptyResponse (){
        Map<String, Object> response = new HashMap<>();
        response.put("result", "success");
        return response;
    }

    public static Map<String, Object> errorResponse(String errorMessage){
        Map<String, Object> response = new HashMap<>();
        response.put("result", "error");
        response.put("message", errorMessage);
        return response;
    }
}
