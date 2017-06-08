package com.champion.framework.metadata;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/6 0006 15:18
 */
public class AjaxModel{

    private boolean success;

    private Object data;

    public AjaxModel() {}

    public AjaxModel(boolean success) {
        this.success = success;
    }

    public AjaxModel(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object object) {
        this.data = object;
    }
}
