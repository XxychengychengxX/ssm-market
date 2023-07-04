package com.project.ssmproject2.controller;

import com.project.ssmproject2.entity.SmbmsProvider;
import com.project.ssmproject2.service.ISmbmsProviderService;
import com.project.ssmproject2.system.config.WithAuthorizationHeader;
import com.project.ssmproject2.system.model.ProviderAddModel;
import com.project.ssmproject2.system.response.NormalSelectResponse;
import com.project.ssmproject2.system.response.NormalUpdateResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * provider相关前端控制器
 *
 * @author ychengycheng
 * @since 2023-07-01
 */
@RestController
@RequestMapping("/smbms-provider")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5173/", "https://localhost:5173"
        , "https://localhost:5173/","http://192.168.0.107:5173","http://192.168.0.107:5173/"},
        allowCredentials =
        "true")
public class SmbmsProviderController {

    @Resource
    ISmbmsProviderService iSmbmsProviderService;

   /* @GetMapping("/getProviderPageCount")
    @WithAuthorizationHeader
    public NormalUpdateResponse gerProviderPageCount( @RequestHeader("Authorization") String
    authorization){
        return iSmbmsProviderService.gerProviderPageCount();
    }*/

    /**
     * 用户查看当前供应商表
     *
     * @param page 查询的当前页数
     * @return 查询数组的JSON字符串
     */
    @GetMapping("/providersGet/{page}")
    @WithAuthorizationHeader
    public NormalSelectResponse selectProviderInPage(@PathVariable("page") Integer page,
                                                     @RequestHeader("Authorization") String authorization) {
        return iSmbmsProviderService.selectProviderInPage(page, authorization);

    }

    /**
     * 管理员添加供应商
     *
     * @param authorization 身份令牌
     * @param provider      用于封装请求体的对象
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */

    @WithAuthorizationHeader
    @PostMapping("/providerAdd")
    public NormalUpdateResponse providerAdd(@RequestHeader("Authorization") String authorization,
                                            @RequestBody ProviderAddModel provider) {
        return iSmbmsProviderService.providerAdd(authorization, provider);
    }

    /**
     * 管理员更新
     *
     * @param authorization 身份令牌
     * @param provider      用于封装请求体的对象
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    @PutMapping("/providerUpdate")
    @WithAuthorizationHeader
    public NormalUpdateResponse providerUpdate(@RequestHeader("Authorization") String authorization,
                                               @RequestBody SmbmsProvider provider) {
        return iSmbmsProviderService.providerUpdate(authorization, provider);
    }

    /**
     * 管理员删除供应商
     *
     * @param authorization 身份令牌
     * @param id            要删除的供应商的id
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    @DeleteMapping("/providerDelete/{Id}")
    @WithAuthorizationHeader
    public NormalUpdateResponse providerDelete(@RequestHeader("Authorization") String authorization,
                                               @PathVariable("Id") Long id) {
        return iSmbmsProviderService.providerDelete(authorization, id);
    }
}
