package com.ram.rupia.api.controller;


import com.ram.rupia.api.dto.AdminLoginDTO;
import com.ram.rupia.api.dto.LoginDTO;
import com.ram.rupia.api.dto.OtpDTO;
import com.ram.rupia.api.post_request.UserLoginRequest;
import com.ram.rupia.api.post_request.VerifyOtpRequest;
import com.ram.rupia.api.response.ApiResponse;
import com.ram.rupia.domain.entity.UserEntity;
import com.ram.rupia.service.auth.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ram Mandal on 04/12/2025
 *
 * @System: Apple M1 Pro
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")//remove once the front end is hosted on a real web server
public class AuthController {

    private final AuthServiceImpl authService;


    @PostMapping("/super-admin/login")
    public ResponseEntity<ApiResponse<AdminLoginDTO>> loginSuperAdmin(@RequestBody UserLoginRequest request) {
        ApiResponse<AdminLoginDTO> apiResponse = new ApiResponse<>(true, "success", authService.loginSuperAdmin(request.getMobileNumber()));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<ApiResponse<AdminLoginDTO>> loginAdmin(@RequestBody UserLoginRequest request) {
        ApiResponse<AdminLoginDTO> response = new ApiResponse<>(true, "success", authService.loginAdmin(request.getMobileNumber()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("customer/login")
    public ResponseEntity<OtpDTO> loginUser(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(authService.loginUser(request.getMobileNumber()));
    }

    @PostMapping("/otp-verify")
    public ResponseEntity<ApiResponse<LoginDTO>> verifyOtp(@RequestBody VerifyOtpRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", authService.verifyLoginOtp(request)));
    }
}
