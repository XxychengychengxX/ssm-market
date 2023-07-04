package com.project.ssmproject2.controller;

import com.project.ssmproject2.entity.SmbmsBill;
import com.project.ssmproject2.service.ISmbmsBillService;
import com.project.ssmproject2.system.config.WithAuthorizationHeader;
import com.project.ssmproject2.system.response.NormalUpdateResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * bill相关前端控制器
 *
 * @author ychengycheng
 * @since 2023-07-01
 */
@RestController
@RequestMapping("/smbms-bill")
@CrossOrigin(origins = {"https://localhost:5173"})
public class SmbmsBillController {
    @Resource
    ISmbmsBillService iSmbmsBillService;

    /**
     * 管理员添加订单
     *
     * @param authorization 身份令牌
     * @param bill          用于封装请求体的对象
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    @PostMapping("/billAdd")
    @WithAuthorizationHeader
    public NormalUpdateResponse billAdd(@RequestHeader("Authorization") String authorization,
                                        @RequestBody SmbmsBill bill) {
        return iSmbmsBillService.billAdd(authorization, bill);
    }

    /**
     * 管理员更新订单
     *
     * @param authorization 身份令牌
     * @param bill          用于封装请求体的对象
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    @PutMapping("/billUpdate")
    @WithAuthorizationHeader
    public NormalUpdateResponse billUpdate(@RequestHeader("Authorization") String authorization,
                                           @RequestBody SmbmsBill bill) {
        return iSmbmsBillService.billUpdate(authorization, bill);
    }

    /**
     * 管理员删除订单
     *
     * @param authorization 身份令牌
     * @param id            要删除的供应商的id
     * @return 成功返回200对应的公共相应，反之返回出错原因
     */
    @DeleteMapping("/billDelete/{Id}")
    @WithAuthorizationHeader
    public NormalUpdateResponse billDelete(@RequestHeader("Authorization") String authorization,
                                           @PathVariable("Id") Long id) {
        return iSmbmsBillService.billDelete(authorization, id);
    }

}
