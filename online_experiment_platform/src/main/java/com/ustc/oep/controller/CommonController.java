package com.ustc.oep.controller;

import com.ustc.oep.common.R;
import com.ustc.oep.entity.Assignment;
import com.ustc.oep.service.AssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YuJianhua
 * @create 2023-03-17 11:26
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${online-experiment-platform.path}")
    private String bassPath;

    @Autowired
    private AssignmentService assignmentService;
}
