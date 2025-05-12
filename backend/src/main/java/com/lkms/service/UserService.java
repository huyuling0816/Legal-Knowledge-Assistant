package com.lkms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lkms.po.userPo.UserPo;
import com.lkms.vo.userVo.UserVo;

public interface UserService extends IService<UserPo> {
    UserVo userRegister(UserVo user);

    UserVo userLogin(String email, String password);

    UserVo getUser(Integer id);

    UserVo userLogout(Integer id);
}
