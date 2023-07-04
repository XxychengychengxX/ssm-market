package com.project.ssmproject2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 地址相关前端控制器
 *
 * @author ychengycheng
 * @since 2023-07-01
 */
@RestController
@RequestMapping("/smbms-address")
@CrossOrigin(origins = {"http://localhost:5173/","http://localhost:5173"},allowCredentials = "true")
public class SmbmsAddressController {

}
