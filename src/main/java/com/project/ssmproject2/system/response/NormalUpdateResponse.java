/**
 * @author Valar Morghulis
 * @Date 2023/7/2
 */
package com.project.ssmproject2.system.response;

import lombok.Data;

/**
 * 更新表元素封装，一般来说响应给前端的字符串是 { ”code“：200（表示完成对应请求）||500（表示出错） ”message“： （更新成功或者更新失败） }
 */
@Data
public class NormalUpdateResponse {
    int code;
    String message;

    /**
     * 封装通常更新响应的JSON字符串
     *
     * @param res     数据库查询结果成功否
     * @param code    响应码(系统)
     * @param message 传入的信息
     * @return 通常更新响应的JSON字符串
     */
    public static NormalUpdateResponse generate(boolean res, int code, String message) {
        NormalUpdateResponse normalUpdateResp =
                new NormalUpdateResponse();
        //如果持久层出了问题
        if (res) {
            normalUpdateResp.setCode(code);
            normalUpdateResp.setMessage(message);
            return normalUpdateResp;
        }
        normalUpdateResp.setCode(5);
        normalUpdateResp.setMessage("持久层出错,请检查后重试。原因可能是字段有错或者服务器内部异常");
        return normalUpdateResp;

    }

}

