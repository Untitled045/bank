package cn.edu.nwu.controller;


import cn.edu.nwu.domain.ChangePasswordVO;
import cn.edu.nwu.domain.StateSwitchVO;
import cn.edu.nwu.domain.User;
import cn.edu.nwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session, HttpServletResponse response) {
        User login = userService.login(user);
        if(login==null){
            return "用户名或密码错误";
        }
        if(login.getState()==0){
            return "账户被禁用,无法登录";
        }
        session.setAttribute("loginName", login.getUsername());
        Cookie cookie1 = new Cookie("card",login.getCard() );
        cookie1.setPath("/");
        cookie1.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("username",login.getUsername() );
        cookie2.setPath("/");
        cookie2.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie2);
        try {
            if(login.getAdmin()==0){
                return "success";
//                response.sendRedirect("/");
            }else{
                return "admin_success";
//                response.sendRedirect("/index_admin.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "haha";
    }
    @GetMapping("/logout")
    public String logout(User user, HttpSession session, HttpServletResponse response) {
        if(session!=null){
            session.invalidate();//关闭session
        }

        try {
            response.sendRedirect("/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "haha";
    }
    @GetMapping("/admin/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        if(session!=null){
            session.invalidate();//关闭session
        }

        try {
            response.sendRedirect("/admin.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "haha";
    }

    @PostMapping("/modify")
    public boolean modify(@RequestBody User user){

        return userService.modify(user);
    }
    @GetMapping("/list")
    public Page<User> getUsers(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
        return userService.getUsers(pageNum,pageSize);
    }
    @PostMapping("/state")
    public boolean stateSwitch(@RequestBody StateSwitchVO stateSwitchVO){
        return userService.stateSwitch(stateSwitchVO.getCard(),stateSwitchVO.getNewState());
    }

    @PostMapping("")
    public String UserRegister(@RequestBody User user){

        return userService.userAdd(user);
    }

    @GetMapping("")
    public User getInfo(@RequestParam("card") String card){
        return userService.getInfo(card);
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestBody ChangePasswordVO changePasswordVO){
        return userService.changePassword(changePasswordVO);
    }
}
