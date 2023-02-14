package com.example.jisoo.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jisoo.mapper.UserMapper;
import com.example.jisoo.model.User;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("join")
    public String join(){
        return "user/join";
    }
    
    @PostMapping("join")
    public String join(HttpSession session, User user){
        
        userMapper.join(user);
        return "redirect:/";
    }

    @GetMapping("login")
    public String login(){
        return "user/login";
    }

    @PostMapping("login")
    public String login(HttpSession session, User user){
        String id = user.getUserId();
        String pw = user.getUserPw();
        String result = "";

        String getPw = userMapper.getPw(id);
        if(getPw != null){
            if(getPw.equals(pw)){
                User userData = userMapper.selectUser(id);
                session.setAttribute("user", userData);
                result="redirect:/";
            }else{
                result="redirect:/user/loginFail";
            }
        }else{
            session.setAttribute("user", null);
            result="user/loginFail";
            
        }

        return result;
    }

    @GetMapping("loginFail")
    public String loginFail(){
        return "user/loginFail";
    }



    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("deleteId")
    public String deleteId(){
        return "user/deleteId";
    }


    @PostMapping("deleteId")
    public String deleteId(HttpSession session, User user){
        String id = user.getUserId();
        String pw = user.getUserPw();
        String result = "";

        String getPw = userMapper.getPw(id);
        if(getPw != null){
            if(getPw.equals(pw)){
               userMapper.userRemove(user);
               session.removeAttribute("user");
               result="user/deleteSuccess";
            }else{
                result="redirect:/user/deleteId";
            }
        }else{
            result="redirect:/user/deleteId";
            
        }
        return result;
    }

    @GetMapping("deleteSuccess")
    public String deleteSuccess(){
        return "/user/deleteSuccess";
    }



}
