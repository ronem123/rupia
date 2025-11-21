package com.ram.rupia.rupia.config;


import com.ram.rupia.rupia.dto.CustomerDTO;
import com.ram.rupia.rupia.entity.Customer;
import com.ram.rupia.rupia.post_request.CustomerRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by Ram Mandal on 19/11/2025
 *
 * @System: Apple M1 Pro
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    //map the respective fields from entity with dto class
    //if not provided it will try to see the same fields between entity and dto
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "contact", source = "entity.contact")
    CustomerDTO toCustomerDTO(Customer entity);

    //map the respective fields from dto with entity class
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "contact", source = "dto.contact")
    Customer toCustomer(CustomerDTO dto);

    //Map the respective fields from CustomerRequestBody to Customer
    @Mapping(target = "name", source = "body.name")
    @Mapping(target = "contact", source = "body.contact")
    Customer toCustomer(CustomerRequestBody body);
}
