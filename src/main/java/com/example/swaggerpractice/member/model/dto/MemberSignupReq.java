package com.example.swaggerpractice.member.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberSignupReq {
    String email;
    String password;
}
