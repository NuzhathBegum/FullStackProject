package fullproject.fullproject.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

import fullproject.fullproject.entity.Student;



public interface StudentInterface extends JpaRepository<Student,Integer>{

}
