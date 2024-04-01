package com.example.form_creation.Repository;

import java.util.Optional;
import com.example.form_creation.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{
    Optional <UserInfo> findByName(String username);
}
