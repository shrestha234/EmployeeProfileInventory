package Texo.testboot.caremanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Texo.testboot.caremanagement.model.Employ;

@RestController
public class EmpListController {
	
	private ArrayList<Employ> empList = new ArrayList<>();	
	@GetMapping("/showAllEmploys")
	public List<Employ>  showAll(){
		
		return empList;	
	}  
	
	@PostMapping("/postEmploys")
	public String postEmploys(@RequestBody Employ employ) {
		
		empList.add(employ);
		return "Record added successfully";
		
	}
	
	@DeleteMapping("/deleteEmploy/{eid}")
	public String deleteEmploys(@PathVariable("eid") int eid) {
		
		boolean status = false;
		for (Employ employ : empList) {
			if(employ.getEmpid() == eid) {
				empList.remove(employ);
				status = true;
				break;
			}			
		}
		
		if(status == true) {
			return "successfully deleted";
		}else {
			return "no record found with "+eid;
		}
		
	}
	
	@PutMapping("/updateEmploy")
	public String updateEmploy(@RequestBody Employ emp) {
		
		boolean status = false;
		for (Employ employ : empList) {
			if(employ.getEmpid() == emp.getEmpid()) {
			
				employ.setEmpName(emp.getEmpName());
				employ.setEmpAddress(emp.getEmpAddress());
				status = true;
				break;
			}			
		}
		
		if(status == true) {
			return "successfully updated!!";
		}else {
			return "no record found with "+emp.getEmpid();
		}

	}
	
	@GetMapping("/searchEmploy/{eid}")
    public ResponseEntity<?> showAllEmploy(@PathVariable int eid) {
		
		for (Employ employ : empList) {
			if(employ.getEmpid() == eid) {	
			return ResponseEntity.ok(employ);
			}			
		}
		
			return ResponseEntity.ok("no record found with "+eid);
	}
	
	
}
