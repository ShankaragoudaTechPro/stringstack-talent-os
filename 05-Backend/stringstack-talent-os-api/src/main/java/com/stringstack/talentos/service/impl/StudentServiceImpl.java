package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.student.StudentRequest;
import com.stringstack.talentos.dto.student.StudentResponse;
import com.stringstack.talentos.entity.Student;
import com.stringstack.talentos.entity.User;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.StudentMapper;
import com.stringstack.talentos.repository.StudentRepository;
import com.stringstack.talentos.repository.UserRepository;
import com.stringstack.talentos.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public StudentResponse createStudent(StudentRequest request) {

        if (studentRepository.existsByStudentCode(request.getStudentCode())) {
            throw new DuplicateResourceException("Student Code already exists.");
        }

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (studentRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateResourceException("Phone already exists.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        Student student = StudentMapper.toEntity(request);

        student.setUser(user);

        Student savedStudent = studentRepository.save(student);

        return StudentMapper.toResponse(savedStudent);
    }

    @Override
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found."));

        return StudentMapper.toResponse(student);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found."));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        student.setStudentCode(request.getStudentCode());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setGender(request.getGender());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setQualification(request.getQualification());
        student.setCollege(request.getCollege());
        student.setPassingYear(request.getPassingYear());
        student.setPercentage(request.getPercentage());
        student.setSkillSet(request.getSkillSet());
        student.setActive(request.getActive());
        student.setUser(user);

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.toResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found."));

        studentRepository.delete(student);
    }
}