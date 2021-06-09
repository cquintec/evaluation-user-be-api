package com.bci.evaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bci.evaluation.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query(value="SELECT user_id,name,email,password FROM user u WHERE u.email=:email", nativeQuery = true)
    User findUserByMail(@Param("email") String mail);

}
