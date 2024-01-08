package com.kostis.teachersdemo.repo;

import com.kostis.teachersdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    List<User> findByRole_Name(String name);

    @Query("SELECT u FROM User u " +
            "WHERE u.role.id = 1 " +
            "AND (u.firstname LIKE ?1 OR u.lastname LIKE ?1 OR u.username LIKE ?1 OR u.email LIKE ?1 OR CAST(u.startYear AS string) LIKE ?1) " +
            "ORDER BY u.id")
    List<User> findTeachersWithCustomSearch(String searchValue);


    @Query("SELECT u FROM User u " +
            "WHERE u.role.id = 2 " +
            "AND (u.firstname LIKE ?1 OR u.lastname LIKE ?1 OR u.username LIKE ?1 OR u.email LIKE ?1 OR CAST(u.startYear AS string) LIKE ?1 OR CAST(u.semester AS string) LIKE ?1) " +
            "ORDER BY u.id")
    List<User> findStudentsWithCustomSearch(String searchValue);


}
