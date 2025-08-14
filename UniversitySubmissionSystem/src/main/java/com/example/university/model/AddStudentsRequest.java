package com.example.university.model;

import java.util.List;

public class AddStudentsRequest {
    private List<StudentDTO> students;

    // Getter and Setter
    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}
