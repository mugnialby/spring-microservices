package com.alby.userservice.repository;

import com.alby.userservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    
    boolean existsByUsername(String username);
    
}
