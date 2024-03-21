package com.example.demo.model;

public class ResponseMessage {
    private String respType;
    private String respMsg;
    private Object resp;

    public ResponseMessage() {}

    public ResponseMessage(String respType, String respMsg, Object resp) {
        this.respType = respType;
        this.respMsg = respMsg;
        this.resp = resp;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public Object getResp() {
        return resp;
    }

    public void setResp(Object resp) {
        this.resp = resp;
    }
    
}
