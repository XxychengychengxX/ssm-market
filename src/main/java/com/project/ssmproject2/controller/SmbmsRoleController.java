package com.project.ssmproject2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ychengycheng
 * @since 2023-07-01
 */
@RestController
@RequestMapping("/smbms-role")
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5173/"},allowCredentials = "true")

public class SmbmsRoleController {

}
