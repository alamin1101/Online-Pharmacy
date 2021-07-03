package com.pharm.online.repository;

import com.pharm.online.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    int countUsersByUsername(String username);
    @Query("select user from User user where user.role='ROLE_ADMIN'")
    List<User> findByRole();

//
//    @Query("select b from User b where b.role=?2 and b.bookBorrowList.size > 0 and (?1 is null or b.username like %?1% or b.email like %?1% or b.phone like %?1%)")
//    List<User> findAllByRoleAndSearchValue(String s, String role);


    @Query("select new User(b.username,b.email,b.phone) from User b WHERE b.role='ROLE_USER' and (?1 is null or b.username like %?1% or b.email like %?1% or b.phone like %?1%)  ")
    List<User> findAllUsers(String s);
    
    
    

}
