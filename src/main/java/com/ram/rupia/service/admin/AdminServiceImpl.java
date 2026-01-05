/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:05/01/2026
 * Time:12:23
 */


package com.ram.rupia.service.admin;

import com.ram.rupia.api.dto.AdminDTO;
import com.ram.rupia.api.post_request.CreateAdminRequest;
import com.ram.rupia.config.AdminMapper;
import com.ram.rupia.domain.entity.Admin;
import com.ram.rupia.domain.entity.UserEntity;
import com.ram.rupia.domain.enums.AdminStatus;
import com.ram.rupia.domain.enums.UserRole;
import com.ram.rupia.exception.BadRequestException;
import com.ram.rupia.repository.AdminRepository;
import com.ram.rupia.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final AdminMapper adminMapper;

    @Transactional
    @Override
    public AdminDTO createNewAdmin(CreateAdminRequest request) {
        System.out.println("CreateAdminRequest: "+request);

        boolean isEmailExists = adminRepository.existsByEmail(request.getEmail());
        if (isEmailExists) {
            throw new BadRequestException("Admin already exists with " + request.getEmail());
        }
        UserEntity user = UserEntity.builder()
                .mobileNumber(request.getMobileNumber())
                .userRole(UserRole.ADMIN)
                .build();

        user = userRepository.save(user);

        Admin admin = Admin.builder()
                .name(request.getFullName())
                .email(request.getEmail())
                .user(user)
                .status(AdminStatus.ACTIVE)
                .build();

        return adminMapper.toAdminDTO(adminRepository.save(admin));
    }
}