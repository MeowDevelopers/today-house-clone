package com.example.be_study.service.user.domain;

import com.example.be_study.service.base.AbstractBaseUserDeleteEntity;
import com.example.be_study.service.user.dto.UserSignUpRequest;
import com.example.be_study.service.user.enums.DeviceType;
import com.example.be_study.service.user.enums.ProviderType;
import com.example.be_study.service.user.enums.UserType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "user")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@NoArgsConstructor
public class User extends AbstractBaseUserDeleteEntity {

    @Column(name = "user_profile", nullable = true)
    private String userProfile;

    @Column(name = "user_email", nullable = true)
    private String userEmail;

    @Column(name = "user_password", nullable = true)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @Column(name = "user_nickname", nullable = false)
    private String userNickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type", nullable = false)
    private ProviderType providerType;

    @Column(name = "provider_key", nullable = true)
    private String providerKey;

    @Column(name = "user_last_login_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime userLastLoginDate;

    @Column(name = "dormancy", nullable = false, columnDefinition = "boolean default false")
    private Boolean dormancy;

    @Column(name = "refresh_token", nullable = true)
    private String refreshToken;

    @Builder
    public User(String userProfile,
                String userEmail, String userPassword, UserType userType, String userNickName, ProviderType providerType, String providerKey, LocalDateTime userLastLoginDate, Boolean dormancy) {
        this.userProfile = userProfile;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType = userType;
        this.userNickName = userNickName;
        this.providerType = providerType;
        this.providerKey = providerKey;
        this.userLastLoginDate = userLastLoginDate;
        this.dormancy = dormancy;

        this.setDeleted(false);
    }

    public static User ofKakao() {
        return User.builder()
                .build();
    }

    public static User ofOrigin(UserSignUpRequest request, PasswordEncoder passwordEncoder) {
        return User.builder()
                .userEmail(request.getUserEmail())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .userNickName(request.getUserNickname())
                .providerType(ProviderType.ORIGIN)
                .dormancy(false)
                .userType(UserType.BASIC_USER)
                .build();
    }

    /**
     *  RefreshToken 업데이트
     */
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
