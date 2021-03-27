package com.twgroup.login.test;

import com.twgroup.login.model.RoleType;
import com.twgroup.login.model.User;
import com.twgroup.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test/user/lists")
    public List<User> userList() {
        return userRepository.findAll();
    }

    @GetMapping("/test/user/list")
    public List<User> userPageList(@PageableDefault(size=2, sort="id",direction= Sort.Direction.DESC) Pageable pageable) {
        Page<User> userPageList=userRepository.findAll(pageable);

        List<User> users=userPageList.getContent();
        return users;
    }

    @Transactional
    @PutMapping("/test/user/{id}")
    public String update(@PathVariable int id, @RequestBody User requestUser) {
        User user=userRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("업데이트할려고하는 유저가 없습니다.");
                });
        user.setUsername(requestUser.getUsername());
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        return "업데이트 완료";
    }

    @DeleteMapping("/test/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패했습니다. 해당 id는 없습니다.";
        }
        return "삭제 완료"+id;
    }
    @GetMapping("/test/user/{id}")
    public User findOne(@PathVariable int id) {
        User user=userRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("찾는 아이디가 없습니다.");
                });
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        return user;
    }

    @PostMapping("/test/user")
    public String insert(@RequestBody User user) {
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getRole());
        System.out.println(user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "완료";
    }
}
