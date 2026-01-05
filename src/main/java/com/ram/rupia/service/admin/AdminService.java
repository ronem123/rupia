package com.ram.rupia.service.admin;

import com.ram.rupia.api.dto.AdminDTO;
import com.ram.rupia.api.post_request.CreateAdminRequest;

public interface AdminService {
    public AdminDTO createNewAdmin(CreateAdminRequest request);
}
