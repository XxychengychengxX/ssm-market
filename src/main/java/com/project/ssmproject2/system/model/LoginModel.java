/**
 * @author Valar Morghulis
 * @Date 2023/7/1
 */
package com.project.ssmproject2.system.model;

import lombok.Data;

/**
 * Register & Login model
 */
@Data
public class LoginModel {
    private String userCode;
    private String userPassword;
}