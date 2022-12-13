package fullproject.fullproject.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fullproject.fullproject.Interface.StudentInterface;
import fullproject.fullproject.Service.Studentservice;
import fullproject.fullproject.entity.Student;


@Service
public class StudentImp  implements Studentservice{
	private StudentInterface studentInt;
	public StudentImp(StudentInterface studentInt) {// constructor
		this.studentInt=studentInt;
	}
	public Student saveStudent(Student stdobj) {
		return studentInt.save(stdobj);
	}
	public List<Student>readAllStudents(){
		return studentInt.findAll();
	}
	public Student fetchById(int id) throws Exception {
		Optional<Student> stdobj = studentInt.findById(id);	
		if(stdobj.isPresent()) {
			return stdobj.get();
		}
		else
		{
			throw new Exception("not found"); 
		}
	}
	public Student updatestudent(int id, Student value){
		Optional<Student> stdobj = studentInt.findById(id);	
		if(stdobj.isPresent()) {
			Student objFromDB = stdobj.get();
			objFromDB.setFirstname(value.getFirstname());
			objFromDB.setLastname(value.getLastname());
			objFromDB.setFavSub(value.getFavSub());

			studentInt.save(objFromDB);
			return objFromDB;
		}
		return new Student();
	}
	public void deleteStudent(int id) {
		Optional<Student> stdobj = studentInt.findById(id);	
		if(stdobj.isPresent()) {
			studentInt.deleteById(id);
		}

	}
	@Override
	public Student getStdntById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Student> viewStudentsFromDB() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}


