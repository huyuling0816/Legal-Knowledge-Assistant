package com.lkms.controller;

import com.lkms.po.userPo.UserPo;
import com.lkms.service.UserService;
import com.lkms.utils.ResultHelper;
import com.lkms.vo.ResultVo;
import com.lkms.vo.userVo.UserVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    ResultHelper resultHelper;

    @PostMapping("/login")
    public ResultVo<UserVo> login(@RequestBody @Valid @NotNull UserVo userVo) {
        UserVo userVo1 = userService.userLogin(userVo.getEmail(), userVo.getPassword());
        return resultHelper.success(userVo1);
    }

    @PostMapping("/logout")
    public ResultVo<UserVo> logout(@RequestParam Integer id) {
        UserVo userVo = userService.userLogout(id);
        return resultHelper.success(userVo);
    }

    @PostMapping("/register")
    public ResultVo<UserVo> register(@RequestBody @Valid @NotNull UserVo userVo) {
        userVo = userService.userRegister(userVo);
        return resultHelper.success(userVo);
    }
}
