package com.vinschool.smarttime.model.projection;

import java.util.Date;
import java.util.List;

import com.vinschool.smarttime.model.dto.RoleDto;

public interface UserProjection {
    String getId();

    String getFullName();

    String getPhone();

    String getEmail();

    String getDisplayName();

    boolean isActive();

    String getAvatar();

    String getPassword();

    Date getCreateDate();

    Date getUpdateDate();

    List<RoleProjection> getRole();
}
