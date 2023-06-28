package com.ustc.oep.controller;

/**
 * @author YuJianhua
 * @create 2023-03-16 21:11
 */
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ustc.oep.common.R;
import com.ustc.oep.dto.UserDTO;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.service.LoginService;
import com.ustc.oep.service.UserInfoService;
import com.ustc.oep.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    LoginService LoginService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RedisCache redisCache;

    @Value("${online-experiment-platform.avatar-path}")
    private String avatarPath;

    @PostMapping("/login")
    public R<UserDTO> login(@RequestBody UserInfo userInfo) {
        return LoginService.login(userInfo);
    }

    @PostMapping("/logout")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public R<String> logout(){
        //获取SecurityContextHolder中的用户信息
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long uuid = loginUser.getUserInfo().getId();
        redisCache.deleteObject("login:"+uuid);
        return R.success("退出成功");
    }

    /**
     * 注册
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public R<String> save(@RequestBody UserDTO userDTO){
//        log.info(userInfo.toString());
//        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//
//        userInfoService.save(userInfo);
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return R.error("参数错误");
        }
        UserInfo r = userInfoService.register(userDTO);
        if(r == null){
            return R.error("用户名已存在");
        }else {
            return R.success("注册成功");
        }
    }


    @GetMapping("/info")
    public R<UserInfo> info(){
        //获取SecurityContextHolder中的用户信息
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long uuid = loginUser.getUserInfo().getId();
        UserInfo userInfo = loginUser.getUserInfo();
        return R.success(userInfo);
    }

    /**
     * 上传头像
     * @Param userInfo 用户信息
     * @Param file 头像文件
     * @return 上传结果
     **/
    @PostMapping("/avatarUpload")
    public R<String> avatarUpload(MultipartFile file){
        log.info(file.toString());

        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        File dir = new File(avatarPath);

        if(!dir.exists())
            dir.mkdirs();

        String fileName = UUID.randomUUID().toString() + suffix;
        try{
            //将临时文件转存到指定目录
            file.transferTo(new File(avatarPath + fileName));
        } catch (Exception e){
            e.printStackTrace();
        }

        //获取SecurityContextHolder中的用户信息
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = loginUser.getUserInfo();
        //更新用户头像
        userInfo.setAvatarUrl(fileName);
        userInfoService.updateById(userInfo);

        return R.success(fileName);
    }

    /**
     * 获取用户头像
     *
     * @return 头像文件
     * @Param avatar 头像文件名
     **/
//    @GetMapping("/getAvatar/{avatar}")
//    public void getAvatar(@PathVariable String avatar, HttpServletResponse response) {
//        try{
//            FileInputStream fileInputStream = new FileInputStream(new File(avatarPath + avatar));
//            ServletOutputStream outputStream = response.getOutputStream();
//            response.setContentType("image/jpeg");
//            int len = 0;
//            byte[] buffer = new byte[4096];
//            while((len = fileInputStream.read(buffer)) != -1){
//                outputStream.write(buffer, 0, len);
//                outputStream.flush();
//            }
//
//            outputStream.close();
//            fileInputStream.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @GetMapping("/getAvatar/{avatar}")
//    public ResponseEntity<byte[]> getAvatar(@PathVariable String avatar){
//        try{
//            File file = new File(avatarPath + avatar);
//            byte[] content = IOUtils.toByteArray(Files.newInputStream(file.toPath()));
////                byte[] content = Files.readAllBytes(file.toPath());
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.setContentDispositionFormData("attachment", file.getName());
//            headers.setContentLength(content.length);
//            headers.setContentType(MediaType.IMAGE_JPEG);
//            return new ResponseEntity<>(content, headers, HttpStatus.OK);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @GetMapping("/getAvatar/{avatar}")
    public void getAvatar(@PathVariable String avatar, HttpServletResponse response) throws IOException{
        File file = new File(avatarPath + avatar);
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
        response.setContentType("application/octet-stream");

        outputStream.write(FileUtil.readBytes(file));
        outputStream.flush();
        outputStream.close();
    }
    /**
     * 删除用户
     * @Param id 用户id
     * @return 删除结果
     **/
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public R<String> delete(Long id){
        userInfoService.removeById(id);
        return R.success("删除成功");
    }

    /**
     * 批量删除用户
     * @Param ids 用户id集合
     * @return 删除结果
     **/
    @PostMapping("/del/batch")
    public R<String> deleteBatch(@RequestBody List<Integer> ids) {
        userInfoService.removeByIds(ids);
        return R.success("批量删除成功");
    }


    /**
     * 根据用户信息和页面参数查询用户
     * @Param userInfo 用户信息
     * @return 用户信息集合
     **/
    @GetMapping("/page")
    public R<Page<UserInfo>> findPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam(defaultValue = "") String username,
                                      @RequestParam(defaultValue = "") String email,
                                      @RequestParam(defaultValue = "") String address) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("emailAddress", email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }

        return R.success(userInfoService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 更新用户信息
     * @Param userInfo 用户信息
     * @return 更新结果
     **/
    @PostMapping
    public R<String> save(@RequestBody UserInfo userInfo) {
        userInfoService.saveOrUpdate(userInfo);
        return R.success("新增用户成功");
    }

    /**
     * 根据用户名查询用户
     * @Param username 用户名
     * @return 用户信息
     **/
    @GetMapping("/username/{username}")
    public R<UserInfo> findOne(@PathVariable String username){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return R.success(userInfoService.getOne(queryWrapper));
    }

    /**
     * 查询当前用户id
     * @Param id 用户id
     * @return 用户信息
     **/
    @GetMapping("/getId")
    public R<Long> getId(){
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return R.success(loginUser.getUserInfo().getId());
    }
}
