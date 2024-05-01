package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository implements IStudentRepository{

    @Autowired
    private SessionFactory sessionFactory;

    //1-b
    @Override
    public List<Student> findAll() {
        Session session =sessionFactory.openSession();
        List<Student> allStudent=session.createQuery("FROM Student",Student.class).getResultList();
        session.close();
        return allStudent;
    }

    //2-c
    @Override
    public void saveOrUpdate(Student student) {
        Session session= sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        //insert into ...
        session.saveOrUpdate(student);//db de varsa update eder, yeni objeyi insert eder

        tx.commit();
        session.close();

    }

    //4-b
    @Override
    public void delete(Student student) {
        Session session= sessionFactory.openSession();
        Transaction tx= session.beginTransaction();

        session.delete(student);

        tx.commit();
        session.close();
    }

    //3-b
    @Override
    public Optional<Student> findById(Long id) {
        Session session= sessionFactory.openSession();
        Student student=session.get(Student.class,id);

        Optional<Student> optional=Optional.ofNullable(student);//Attention!!!

        session.close();

        return optional;
    }


}
