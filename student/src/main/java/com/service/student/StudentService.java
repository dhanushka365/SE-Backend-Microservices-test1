package com.service.student;

import com.service.clients.notification.NotificationRequest;
import com.service.amqp.RabbitMQMessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerStudent(StudentRegistrationRequest request){
        Student student = Student.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .course_Id(request.course_Id())
                .build();
        //todo: check if email valid
        //todo: check if email not taken
        //todo: store student in db
        studentRepository.saveAndFlush(student);


        //todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{studentId}",
                FraudCheckResponse.class,
                student.getEmail()
        );

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster found");
        }


        NotificationRequest notificationRequest = new NotificationRequest(
                student.getId(),
                student.getEmail(),
                String.format("Hi %s, welcome to SchoolEase..",
                        student.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }

    public boolean authenticateStudent(String email, String password) {
        Optional<Student> student = studentRepository.findByEmail(email);
        return student.isPresent() && student.get().getPassword().equals(password);
    }

    public void deleteStudent(Integer studentId){
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentRepository.delete(student);
        } else {
            throw new IllegalArgumentException("Student with ID " + studentId + " does not exist");
        }
    }


    public void updateStudent( Integer studentId , StudentUpdateRequest request){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with ID " + studentId + " does not exist"));

        if (request.firstName() != null) {
            student.setFirstName(request.firstName());
        }

        if (request.lastName() != null) {
            student.setLastName(request.lastName());
        }

        if (request.email() != null) {
            student.setEmail(request.email());
        }

        if (request.course_Id() != null) {
            student.setCourse_Id(request.course_Id());
        }
        studentRepository.save(student);
    }

    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

}
