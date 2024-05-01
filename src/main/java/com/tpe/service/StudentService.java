package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

//@Component
@Service//@Component anatasyonunun (gelişmiş)özel halidir
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository repository;

    //1-a
    @Override
    public List<Student> listAllStudents() {

        return repository.findAll();
    }

    //2-b
    @Override
    public void addOrUpdateStudent(Student student) {
        repository.saveOrUpdate(student);
    }

    //3-a
    @Override
    public Student findStudentById(Long id) {

        Student foundStudent=repository.findById(id).
             orElseThrow(()->new StudentNotFoundException("Student Not Found By ID: "+id));//Supplier
        //findById metodunun geriye döndürdüğü optional içinde
        //student varsa foundStudent değşkenine atar.
        //optional objesi boşsa orElseThrow custom exception fırlatılabilir.
        return foundStudent;
    }
    //4-a

    @Override
    public void deleteStudent(Long id) {
        //idsi verilen öğrenciyi bulalım
        Student student=findStudentById(id);
        repository.delete(student);

    }
}
