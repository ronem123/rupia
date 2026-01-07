/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:04/01/2026
 * Time:23:45
 */


package com.ram.rupia.api.controller;

import com.ram.rupia.api.dto.CustomerDTO;
import com.ram.rupia.api.response.ApiResponse;
import com.ram.rupia.service.admin.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")//remove once the front end is hosted on a real web server
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;


    @PostMapping("/approve/{id}")
    ResponseEntity<ApiResponse<CustomerDTO>> approveCustomer(@PathVariable("id") Long id) {
        CustomerDTO customerDto = adminService.approveCustomer(id);
        return new ResponseEntity<>(new ApiResponse<>(true, "Approved", customerDto), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        adminService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}