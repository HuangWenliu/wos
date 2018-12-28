package com.icerno.frame.exception;

/**
 * 自定义异常
 *
 * @author HWL
 */
public class WosException extends RuntimeException {

    private static final long serialVersionUID = 200901080101009010L;
    private String errMsg;     // 错误信息

    public WosException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public WosException(String errMsg, Throwable t) {
        super(errMsg, t);
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
