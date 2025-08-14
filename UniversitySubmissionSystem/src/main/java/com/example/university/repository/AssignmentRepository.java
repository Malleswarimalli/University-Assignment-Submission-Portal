    package com.example.university.repository;

    import com.example.university.model.Assignment;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import java.util.List;

    @Repository
    public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
        // New Method: Find all assignments for a specific course ID
        List<Assignment> findAllByCourseId(Long courseId);
    }
    