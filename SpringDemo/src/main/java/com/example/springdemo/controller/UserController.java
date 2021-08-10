package com.example.springdemo.controller;


import com.example.springdemo.entyty.User;
import com.example.springdemo.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private String productImages = "SpringDemo/images/";

    @GetMapping("/users")
    public String users(ModelMap modelMap) {
        List<User> all = userRepository.findAll();
        modelMap.addAttribute("users", all);
        return "users";
    }

    @GetMapping("/addUser")
    public String addUsers() {
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUsersPost(@ModelAttribute User user, @RequestParam("image") MultipartFile multipartFile) {
        if (multipartFile != null && multipartFile.getOriginalFilename()!=null) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPicUrl(fileName);
            try {
                FileUploadUtil.saveFile(productImages, fileName, multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam int id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/productImage")
    void productImage(@RequestParam("productUrl") String productUrl, HttpServletResponse response) throws IOException {
        InputStream in = new FileInputStream(productImages + File.separator + productUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
