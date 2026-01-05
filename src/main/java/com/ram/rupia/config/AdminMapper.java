package com.ram.rupia.config;

import com.ram.rupia.api.dto.AdminDTO;
import com.ram.rupia.api.post_request.CreateAdminRequest;
import com.ram.rupia.domain.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "fullName", source = "name")
    @Mapping(target = "id", source = "user.id")
    AdminDTO toAdminDTO(Admin admin);

    Admin toAdmin(CreateAdminRequest request);

}
