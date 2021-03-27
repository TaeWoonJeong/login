package com.twgroup.login.controller.api;

import com.twgroup.login.dto.ResponseDto;
import com.twgroup.login.model.RoleType;
import com.twgroup.login.model.User;
import com.twgroup.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private HttpSession session;

    @PostMapping("/user/api/join")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save 호출됨");
        user.setRole(RoleType.USER);
        userService.joinUser(user);
        return new ResponseDto<Integer> (HttpStatus.OK.value(),1);
    }

    @PostMapping("/user/api/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) { //HttpSession session을 빼고 위에 @Autowired하는 방법도 있다.
        System.out.println("로그인 호출됨");
        User principal=userService.login(user);
        if(principal!=null) {
            session.setAttribute("principal",principal);
        }

        return new ResponseDto<Integer> (HttpStatus.OK.value(),1);
    }
}
