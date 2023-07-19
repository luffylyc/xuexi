package com.xuecheng.orders.config;
 /**
 * @description 支付宝配置参数
 * @author Mr.M
 * @date 2022/10/20 22:45
 * @version 1.0
 */
 public class AlipayConfig {
  // 商户appid
//	public static String APPID = "";
  // 私钥 pkcs8格式的
//	public static String RSA_PRIVATE_KEY = "";
  // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
  public static String notify_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
  // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
  public static String return_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
  // 请求网关地址
  public static String URL = "https://openapi.alipaydev.com/gateway.do";
  // 编码
  public static String CHARSET = "UTF-8";
  // 返回格式
  public static String FORMAT = "json";
  // 支付宝公钥
//	public static String ALIPAY_PUBLIC_KEY = "";
  // 日志记录目录
  public static String log_path = "/log";
  // RSA2
  public static String SIGNTYPE = "RSA2";
  public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCV9ptVzCk8cchTCumSNl9lYvDPDHYtRnSNwsbDtF/0hesqAI8wwDEl8t5vby7fASq5wynGq84AwSWhqeF6EybO1sc/smCUm4qp+7viOkJTuUIDr+drFYAX5gJe6lzl51TpRuEMJGAQrwcnRQO2xjYiujHwI8ucVGg+7nd/HDKZz9F8pf+qXJsVC/St5+VdlCa2n7IeWp1EhAShDQT3eRqY84mGRzvqBi06/qFS2qU3finWURd29iqYOirsQULHS+PWIJ8jD7w6ngVtpQJ3FT4e98u00K1hZUP0mbdidJU9Mr7bP8X3tFJuChA3CoM3Cyzvt5pU02ii5FdUpx+bHBD5AgMBAAECggEAURZ4perwPTPVOzoCVEI8UQkv5vT5DcA0E9dHPNlSrVigcSS+SIET0k5CAoVfFcx+utWRyFBr4rKN052VQzoJ2Pv/3yxn1tNIJmPtEwWnibf5vx6lhpyol8OScppNQy9UFZNy6urcWwkZWB2URYdmDF8b05q3ZT+q4qR1TvEDV3FX5EpOx5flxtGyEzD1cnNAA/Gp/vZcdV/lBElaMqA1giXmR1QP5AAbasU2XVq3nX/uj81kNv1fdQh9iQRvAHqisR93ANbRga8ft8Evp1nvSKVOHyexxoBqmLTOFv/jbtBvD1FgPl4m6lziHfbZSlPre9XkUoFv2VE87xqTcn87EQKBgQDZwooAsa8hLNaqbsKKOAmOtET4tpQYmbCCWwuenJBCnVBrKLh2HL8o0Mi605S8EUqQ8vqWkhipitfQi4/uAJz9KTJM59qRxNjeXpG1DljlQNvWJA0QKQRriVtVs9g3gjkZe26LKgTJeSAW55QsopNbu53my6YtX0elpXIi7fpKbQKBgQCwTEBPm30+2ik/jHGD1kQ0xT+GptFHce8YR8P2cmnuEH18OfR27cdSPamK5/TtzqRDfnfiyOzqxhyZsXN06q0dkN0+GHYK+yawdkAh0oOIRm2SSes3k23n5FweDTQC1PJdciJqy4BLbOhSxHMKzEl7tyZdLvbJyF5XVmXIILSJPQKBgCBXAPHLcavmodbb7i5i1iCIaznYu7PPl2Q0q0waCl6qSFgxyvtXl5gzMax1YcBwfPd77nVQKzN6uERzhaOeGbKLXLoJMO+3IVIdD1ypTdB2Sm/KGNqOjOZ/lr3qoLDVuS8soqUuT1mwkHB2Yg4i8umLuo6dLgfM/7H9GS9Q0nwdAoGAXQ6Sr7KSZLfBKBMY5A8Yh4ZKZufs9bpVC0ruTErOUWQQ6J6qxI/0MxvrPy/63//AQwQhgequ66nQ9Otu1xr8b+vEbH9654b0QBba+T9mQGt0cNBlwTsnu0yVtyMe/hOrLtoNArQSLz34T5/0XZHBg/UD4hHlIFOMQKuRJBbnVL0CgYEAoh7C+htJ5wBmKxlk2/8AEI50Xux0Kfnnc+3/Dd0oT3QnlFDXg3uadG6RbxwwFBDG1YdkEl/CqOC9JZQ7J5ndO3VE7ul8mYZ2KSpGGCB6I6ZSE6ytAmUNzbSdhEb9GrjmbGMasSkBlPmIZn/0Lf3EAXVL/egOayFpvv2XtP4SPhA=";
  public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsVoxSlR3OKieQYQYZZ1jdr63uD2D3ZozVcww24mRGrQDvPjHcL8sSsG6lQPZ6cuMNPt8O8U41udoaofXesxmsFGeG1mZ1+kAp0ohDGzIV+MsFLwLbXK+Hd+YqJqfUAe7hNso/LcN4z1rq4QLE+PbfyMQuecIoRrlHE+wF7xuvUF4k6ypJkChW9xMktbVaAX9wxvMVPyJpxseqhDhiA5WN8XnXcJpdiFGThn9jDoiIxpByqrRAk79q2HDZXMdgQd10/2RcELV2d6NQQSeR1Oc5OHf3XdDsKR6n/PiA6TDuh8Bl6OkYqX/0WAabxBeT9VRQuh4atc+WBZKMO+BGAo9mQIDAQAB";
 }
