package com.epam.userentity.repository;

import com.epam.userentity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,String>{
    User getUserByUsername(String username);

    void deleteByUsername(String username);


}
