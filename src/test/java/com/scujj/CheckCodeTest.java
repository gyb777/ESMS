package com.scujj;

import com.scujj.utils.PhoneNumberCheckCodeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CheckCodeTest {
    @Autowired
    PhoneNumberCheckCodeUtil phoneNumberCheckCodeUtil;
    @Test
    public void test(){
        String checkcode = phoneNumberCheckCodeUtil.getCheckCode("18327574066");
        System.out.println(phoneNumberCheckCodeUtil.checkPhoneNumber("18327574066"));
    }
}
