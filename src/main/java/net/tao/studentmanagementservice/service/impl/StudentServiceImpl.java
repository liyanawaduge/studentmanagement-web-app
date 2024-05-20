package net.tao.studentmanagementservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.tao.studentmanagementservice.entity.Student;
import net.tao.studentmanagementservice.repository.StudentRepository;
import net.tao.studentmanagementservice.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getAllStudents() {
		logger.info("Started StudentServiceImpl::getAllStudents()::");
		return studentRepository.findAll();
	}

	@Override
	public void saveStudent(Student student) {
		logger.info("Started StudentServiceImpl::saveStudent()::");
		studentRepository.save(student);
		
	}

	@Override
	public Student getStudentById(long id) {
		logger.info("Started StudentServiceImpl::getStudentById()::id:: "+id);
		Optional<Student> studentOpt = studentRepository.findById(id);
		Student student = null;
		if(studentOpt.isPresent()) {
			student = studentOpt.get();
		}else {
			throw new RuntimeException("Student not found");
		}
		logger.info("End StudentServiceImpl::getStudentById()::id:: "+id);
		return student;
	}

	@Override
	public void deleteStudentById(long id) {
		logger.info("Started StudentServiceImpl::deleteStudentById()::id:: "+id);
		studentRepository.deleteById(id);	
	}

	@Override
	public Page<Student> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return studentRepository.findAll(pageable);
	}

}
