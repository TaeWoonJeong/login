package com.twgroup.login.repository;

import com.twgroup.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    //User findByUsernameAndPassword(String username, String password);

      // 둘이 똑같음
//    @Query(value="SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery = true)
//    User login(String username, String password);
}
