package com.lkms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkms.auth.JwtInfo;
import com.lkms.enums.errorCode.impl.DefaultStatusCode;
import com.lkms.enums.errorCode.impl.UserStatusCode;
import com.lkms.exception.ServiceException;
import com.lkms.mapper.UserInfoMapper;
import com.lkms.po.userPo.UserPo;
import com.lkms.service.UserService;
import com.lkms.utils.EncodeUtil;
import com.lkms.vo.userVo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserPo> implements UserService {
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    EncodeUtil encodeUtil;
    private JwtInfo jwtInfo;

    @Autowired
    public void setJwtInfo(JwtInfo jwtInfo) {
        this.jwtInfo = jwtInfo;
    }

    @Override
    public UserVo userRegister(UserVo user) {
        String email = user.getEmail();
        String password = user.getPassword();
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        if (userInfoMapper.selectOne(wrapper) == null) {
            user.setPassword(encodeUtil.encode(password));
            System.out.println(encodeUtil.encode(password));
            UserPo userPo = new UserPo(user);
            userInfoMapper.insert(userPo);
            userPo = userInfoMapper.selectOne(wrapper);
            if (userPo != null) {
                return new UserVo(user);
            }
        } else {
            throw new ServiceException(UserStatusCode.EMAIL_HAS_BEEN_USED);
        }
        throw new ServiceException(DefaultStatusCode.REQUEST_FAIL);
    }

    @Override
    public UserVo userLogin(String email, String password) {
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        UserPo userPo = userInfoMapper.selectOne(wrapper);
        if (userPo == null) {
            throw new ServiceException(UserStatusCode.EMAIL_NOT_EXIST);
        } else if (!encodeUtil.matches(password, userPo.getPassword())) {
            throw new ServiceException(UserStatusCode.INCORRECT_USERNAME_OR_PASSWORD);
        }
        jwtInfo.setJwtInfo(JwtInfo.builder()
                .id(userPo.getId())
                .email(userPo.getEmail())
                .name(userPo.getName())
                .build());
        return new UserVo(userPo);
    }

    @Override
    public UserVo getUser(Integer id) {
        UserPo userPo = userInfoMapper.selectById(id);
        return userPo == null ? new UserVo() : new UserVo(userPo);
    }

    @Override
    public UserVo userLogout(Integer id) {
        UserVo userVo = UserVo.builder()
                .email(jwtInfo.getEmail())
                .id(jwtInfo.getId())
                .name(jwtInfo.getName())
                .build();
        jwtInfo.setNull();
        return userVo;
    }
}
