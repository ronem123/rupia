package com.ram.rupia.config;


import com.ram.rupia.api.dto.CustomerDTO;
import com.ram.rupia.domain.entity.Customer;
import com.ram.rupia.api.post_request.CustomerRequestBody;
import org.mapstruct.*;

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

    // RequestBody to Entity (default status = KYC_PENDING)
    @Mapping(target = "status", expression = "java(com.ram.rupia.enums.CustomerStatus.KYC_PENDING)")
    Customer toCustomer(CustomerRequestBody body);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequest(CustomerRequestBody body, @MappingTarget Customer entity);

    //List Mapping
    List<CustomerDTO> toCustomersDTO(List<Customer> customers);

    List<Customer> toCustomers(List<CustomerDTO> customerDTOS);
}
