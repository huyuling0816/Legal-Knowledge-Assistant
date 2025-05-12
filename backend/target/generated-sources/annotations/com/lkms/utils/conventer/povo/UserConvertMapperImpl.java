package com.lkms.utils.conventer.povo;

import com.lkms.po.userPo.UserPo;
import com.lkms.po.userPo.UserPo.UserPoBuilder;
import com.lkms.vo.UserVo;
import com.lkms.vo.UserVo.UserVoBuilder;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:09+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class UserConvertMapperImpl implements UserConvertMapper {

    @Override
    public UserVo po2vo(UserPo userPo) {
        if ( userPo == null ) {
            return null;
        }

        UserVoBuilder userVo = UserVo.builder();

        userVo.id( userPo.getId() );
        userVo.name( userPo.getName() );
        userVo.email( userPo.getEmail() );

        return userVo.build();
    }

    @Override
    public UserPo vo2po(UserVo userVo) {
        if ( userVo == null ) {
            return null;
        }

        UserPoBuilder userPo = UserPo.builder();

        userPo.id( userVo.getId() );
        userPo.name( userVo.getName() );
        userPo.password( userVo.getPassword() );
        userPo.email( userVo.getEmail() );

        return userPo.build();
    }
}
