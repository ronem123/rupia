/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:04/01/2026
 * Time:23:44
 */


package com.ram.rupia.api.controller;

import com.ram.rupia.api.dto.AdminDTO;
import com.ram.rupia.api.post_request.CreateAdminRequest;
import com.ram.rupia.api.response.ApiResponse;
import com.ram.rupia.service.admin.AdminServiceImpl;
import com.ram.rupia.service.auth.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/super-admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")//remove once the front end is hosted on a real web server
public class SuperAdminController {
    private final AdminServiceImpl adminService;

    @PostMapping("/create-admin")
    public ResponseEntity<ApiResponse<AdminDTO>> createNewAdmin(@RequestBody CreateAdminRequest request) {
        return new ResponseEntity<>(new ApiResponse<>(true, "success", adminService.createNewAdmin(request)), HttpStatus.CREATED);
    }
}