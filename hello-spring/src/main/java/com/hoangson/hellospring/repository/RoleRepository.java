package com.hoangson.hellospring.repository;

import com.hoangson.hellospring.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT * FROM role r WHERE r.name = :name", nativeQuery = true)
    Role findByName(@Param("name") String name);

}
