package com.example.university.repository;

import com.example.university.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // This method is crucial for the getAllClassesWithStudents service method
    List<User> findByCourseId(Long courseId);
}
