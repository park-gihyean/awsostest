package com.example.jisoo.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jisoo.mapper.SecretMapper;
import com.example.jisoo.model.Secret;
import com.example.jisoo.model.User;

@Controller
@RequestMapping("secret")
public class SecretController {
    @Autowired
    SecretMapper secretMapper;

    @GetMapping("boardList")
    public String boardList(HttpSession session, Model model) {
        ArrayList<Secret> boardList = secretMapper.boardList();
        model.addAttribute("boardList", boardList);
        return "secret/boardList";
    }

    @GetMapping("boardCreate")
    public String boardCreate(Model model ,Secret secret) {

        return "secret/boardCreate";
    }

    @PostMapping("boardCreate")
    public String boardCreate(HttpSession session, Secret secret, Model model) {
        User user = (User) session.getAttribute("user");
        secret.setSecWriter(user.getUserId());
        
        ArrayList<String> iconList = new ArrayList<>();
        iconList.add("🎃");
        iconList.add("👻");
        iconList.add("🧚");
        iconList.add("🦄");
        iconList.add("🧛");
        iconList.add("🧟");
        iconList.add("🤡");
        iconList.add("👽");
        int num = (int)(Math.random()*6+1);
        String icon = (String) iconList.get(num);
        secret.setSecIcon(icon);
        secretMapper.Create(secret);
        return "redirect:/secret/boardList";
    }

    @GetMapping("boardDetail")
    public String boardDetail(HttpSession session, Model model, @RequestParam("secNo") int secNo) {
        ArrayList<Secret> boardList = secretMapper.boardList();
        for (Secret secret : boardList) {
            if (secret.getSecNo() == secNo) {
                model.addAttribute("secret", secret);
            }
        }
        return "secret/boardDetail";
    }

    @GetMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Model model, @RequestParam("secNo") int secNo) {
        ArrayList<Secret> boardList = secretMapper.boardList();
        for (Secret secret : boardList) {
            if (secret.getSecNo() == secNo) {
                model.addAttribute("secret", secret);
            }
        }
        return "secret/boardUpdate";
    }

    @PostMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Secret secret) {
        secretMapper.Update(secret);
        return "redirect:/secret/boardList";
    }

    @GetMapping("boardRemove")
    public String boardRemove(@RequestParam("secNo") int secNo) {
        secretMapper.Remove(secNo);
        return "redirect:/secret/boardList";
    }

    @GetMapping("halloween")
    public String hallo() {
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String now_dt = format.format(now);
        String date="2022-10-28";
        System.out.println("today"+now_dt);

        if (now_dt.equals(date)) {
            result = "redirect:/secret/boardList";
        } else {
            result = "secret/remainDay";
        }
        return result;
    }

}
