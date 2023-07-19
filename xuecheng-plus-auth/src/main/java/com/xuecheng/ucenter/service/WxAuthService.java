package com.xuecheng.ucenter.service;

import com.xuecheng.ucenter.model.po.XcUser;

/**
 * @author cmy
 * @version 1.0
 * @description 微信扫码转入
 * @date 2023/3/9 16:07
 */
public interface WxAuthService {
    /***
     * @description 微信扫码认证,申请令牌，携带令牌查询用户信息，保存用户信息到数据库
     * @param code 授权码
     * @return
    */
    public XcUser wxAuth(String code);
}
