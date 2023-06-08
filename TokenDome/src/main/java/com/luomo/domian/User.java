package com.luomo.domian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: User
 * Package: com.luomo.domian
 * Description:
 *
 * @Author: luomo
 * @Create: 2023/6/8 - 16:49
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersion = -40356785423868321L;
    private Long id;
    private String userName;
    private String nickName;
    private String password;
    private String status;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private String userType;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private Integer delFlag;
}
