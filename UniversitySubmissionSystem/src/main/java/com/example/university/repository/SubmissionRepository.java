    package com.example.university.repository;

    import com.example.university.model.Submission;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import java.util.Optional;

    @Repository
    public interface SubmissionRepository extends JpaRepository<Submission, Long> {
        // Find a submission by a specific student for a specific assignment
        Optional<Submission> findByStudentIdAndAssignmentId(Long studentId, Long assignmentId);
    }
    