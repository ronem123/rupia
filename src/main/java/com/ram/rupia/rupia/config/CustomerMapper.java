package com.ram.rupia.rupia.config;


import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.entity.Customer;
import com.ram.rupia.rupia.post_request.CustomerRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by Ram Mandal on 19/11/2025
 *
 * @System: Apple M1 Pro
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    //Single Object mapping
    @Mapping(target = "customerStatus", source = "entity.status")
    CustomerDTO toCustomerDTO(Customer entity);

    @Mapping(target = "status", source = "dto.customerStatus")
    Customer toCustomer(CustomerDTO dto);

    // RequestBody to Entity (default status = PENDING)
    @Mapping(target = "status", expression = "java(com.ram.rupia.rupia.enums.CustomerStatus.PENDING)")
    Customer toCustomer(CustomerRequestBody body);

    //List Mapping
    List<CustomerDTO> toCustomersDTO(List<Customer> customers);

    List<Customer> toCustomers(List<CustomerDTO> customerDTOS);
}
