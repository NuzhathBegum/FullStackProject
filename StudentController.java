package fullproject.fullproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fullproject.fullproject.Service.Studentservice;
import fullproject.fullproject.entity.Student;
@Controller
public class StudentController {
	private Studentservice studentServ;
	public StudentController (Studentservice studentServ) {// constructor
		this.studentServ=studentServ;
	}
	@GetMapping("/addNewStudent")
	public String createStudent(Model model) {
		Student stdcreateObj= new Student();
		model.addAttribute("stdObj", stdcreateObj);
		return "createStudent";
	}
	@GetMapping("/seeallstudents")// to see all the students
	public String getAllStudentsFromDb(Model model){
		model.addAttribute("studentsList", studentServ.readAllStudents());
		return "studentFrontEnd";

	}
	@PostMapping("/insertStudents")
	public String insertToDb(@ModelAttribute("stdObj")  Student std) {
		studentServ.saveStudent(std);  //obj is insert into db by using save 
		return "redirect:/seeallstudents";
	}
	@GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable int id, Student fromdb,Model model) {

        model.addAttribute("update" ,studentServ.updatestudent(id,fromdb));
        return "updateStudent";
    }
	@PostMapping("/updateAndSaveStudent/{id}")
	public String updateandSave(@PathVariable int id, @ModelAttribute("update")Student newfromdb ,Student fromdb) {
		Student existingdb=studentServ.updatestudent(id,fromdb);
		existingdb.setFirstname(newfromdb.getFirstname());
		existingdb.setLastname(newfromdb.getLastname());
		existingdb.setFavSub(newfromdb.getFavSub());
		return "redirect:/seeallstudents";
	}
	@GetMapping("/deleteStudent/{id}")
	public String deleteFromDb(@PathVariable int id) {
		
		studentServ.deleteStudent(id);
		return "redirect:/seeallstudents";
	}
}




