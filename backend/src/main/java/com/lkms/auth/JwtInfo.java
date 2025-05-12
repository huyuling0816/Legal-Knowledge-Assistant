package com.lkms.auth;

import com.lkms.utils.BeanCopierWithCacheUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class JwtInfo {
    private Integer id;
    private String name;
    private String email;

    public void setJwtInfo(JwtInfo jwtInfo) {
        BeanCopierWithCacheUtil.copy(jwtInfo, this);
    }

    public void setNull() {
        this.id = null;
        this.name = null;
        this.email = null;
    }
}
