package com.ram.rupia.api.controller;


import com.ram.rupia.api.dto.LoginDTO;
import com.ram.rupia.api.dto.OtpDTO;
import com.ram.rupia.api.post_request.CustomerLoginRequestBody;
import com.ram.rupia.api.post_request.VerifyOtpRequest;
import com.ram.rupia.api.response.ApiResponse;
import com.ram.rupia.service.auth.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
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


    @PostMapping("/login")
    public ResponseEntity<OtpDTO> loginUser(@RequestBody CustomerLoginRequestBody requestBody) {
        return ResponseEntity.ok(authService.loginUser(requestBody.getMobileNumber()));
    }

    @PostMapping("/otp-verify")
    public ResponseEntity<ApiResponse<LoginDTO>> verifyOtp(@RequestBody VerifyOtpRequest body) {
        return ResponseEntity.ok(new ApiResponse<>(true, "success", authService.verifyLoginOtp(body)));
    }
}
