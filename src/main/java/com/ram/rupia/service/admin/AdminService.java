package com.ram.rupia.service.admin;

import com.ram.rupia.api.dto.AdminDTO;
import com.ram.rupia.api.dto.CustomerDTO;
import com.ram.rupia.api.post_request.CreateAdminRequest;
import com.ram.rupia.domain.entity.Customer;

public interface AdminService {
    public AdminDTO createNewAdmin(CreateAdminRequest request);
    CustomerDTO approveCustomer(Long id);
    void deleteCustomer(Long id);

}
