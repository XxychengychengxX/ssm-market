/**
 * @author Valar Morghulis
 * @Date 2023/7/2
 */
package com.project.ssmproject2.system.response;

import lombok.Data;

@Data
public class NormalSelectResponse {
    int code;
    String message;

    Object selectRes;

    /**
     * 封装通常查询响应的JSON字符串
     *
     * @param res       数据库查询结果成功否
     * @param message   传入的信息
     * @param selectResult 查询出来的数据库结果
     * @return 通常查询响应的JSON字符串
     */
    public static NormalSelectResponse generate(boolean res, int code, String message,
                                                Object selectResult) {
        NormalSelectResponse normalSelectResponse =
                new NormalSelectResponse();
        //如果持久层出了问题
        if (res) {
            normalSelectResponse.setCode(code);
            normalSelectResponse.setMessage(message);
            normalSelectResponse.setSelectRes(selectResult);
            return normalSelectResponse;
        }
        normalSelectResponse.setCode(5);
        normalSelectResponse.setMessage("持久层出错,请检查服务器后重试");
        return normalSelectResponse;

    }
}