package com.tenet.app.models.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppResponse {

    private Integer code;
    private int statusCode;
    private Boolean success;
    private List data;
    private List<String> errors;

    Map<String, Object> response = new LinkedHashMap<>();


    public AppResponse setCode(Integer code) {
        this.code = code;
        this.statusCode = code;
        return this;
    }

    public AppResponse setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public AppResponse setData(List data) {
        this.data = data;
        return this;
    }

    public AppResponse setErrors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public ResponseEntity<Object> getResponse() {
        response.put("code", code);
        response.put("success", success);
        response.put("data", data);
        response.put("errors", errors);
        return new ResponseEntity(this.response, HttpStatus.OK);
    }
}
