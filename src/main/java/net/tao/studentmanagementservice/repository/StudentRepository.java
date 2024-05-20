package net.tao.studentmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.tao.studentmanagementservice.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
