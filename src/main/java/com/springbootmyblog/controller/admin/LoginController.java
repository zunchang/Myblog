package com.springbootmyblog.controller.admin;


import com.springbootmyblog.model.User;
import com.springbootmyblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    //登录
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes, Model model){
        User user=userService.checkUser(username,password);
        if(user!=null){
            user.setPassword(null);
            session.setAttribute("user", user);
            model.addAttribute("name",user.getNickname());
            return "admin/index";
        }else {
            //重定向传输数据
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }
    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session){

        //清空session
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
