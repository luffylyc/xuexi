package com.xuecheng.base.exception;

/**
 * @author cmy
 * @version 1.0
 * @description 学成在线的统一异常类
 * @date 2023/2/27 13:54
 */
public class XueChengPlusException extends RuntimeException {

    private static final long serialVersionUID = 5565760508056698922L;

    private String errMessage;

    public XueChengPlusException() {
        super();
    }

    public XueChengPlusException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast(CommonError commonError){
        throw new XueChengPlusException(commonError.getErrMessage());
    }
    //该类的实现方法
    public static void cast(String errMessage){
        throw new XueChengPlusException(errMessage);
    }

}
