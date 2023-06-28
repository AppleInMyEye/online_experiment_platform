package com.yinhao.vuehouduan;

import com.yinhao.vuehouduan.entity.User;
import com.yinhao.vuehouduan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class VuehouduanApplication {


    public static void main(String[] args) {
        SpringApplication.run(VuehouduanApplication.class, args);
    }


}
