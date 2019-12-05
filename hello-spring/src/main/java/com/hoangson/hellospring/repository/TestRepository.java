package com.hoangson.hellospring.repository;

import com.hoangson.hellospring.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    @Query(value = "SELECT * FROM test t WHERE t.id = :id", nativeQuery = true)
    TestEntity findUserById(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE test set name = :name where id = :id", nativeQuery = true)
    void updateName(@Param("id") Long id, @Param("name") String name);
    
}
