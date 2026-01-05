/**
 * Author: Ram Mandal
 * Created on @System: Apple M1 Pro
 * User:rammandal
 * Date:05/01/2026
 * Time:12:19
 */


package com.ram.rupia.api.post_request;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class CreateAdminRequest {
    private String mobileNumber;
    private String email;
    private String fullName;
}