package com.appsoft.finalspringproject.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.appsoft.finalspringproject.model.Employee;
import com.appsoft.finalspringproject.model.Product;
import com.appsoft.finalspringproject.repository.ProductRepository;
import com.appsoft.finalspringproject.service.EmployeeService;

@RestController
public class EmployeeRestController {

	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/api/emp/list")
	public List<Employee> getAll() {
		
		return empService.getAllEmp();
	}
	
	@GetMapping("/api/emp/{id}")
	public Employee getOne(@PathVariable("id") int id) {
		
		return empService.getEmpById(id);
	}

	@PostMapping("/api/emp/add")
	public String add(@RequestBody Employee emp) {
		
		empService.addEmp(emp);
		
		return "Added successfully!";
	}
	
	@DeleteMapping("/api/epm/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		empService.deleteEmp(id);
		return "Deleted successfully!";
	}
	
	@PutMapping("/api/epm/update")
	public String update(@RequestBody Employee emp) {
		
		empService.updateEmp(emp);
		return "Updated successfully!";
	}
	
	@GetMapping("/api/emp/j20")
	public String jsonToObject() {
		
		RestTemplate temp = new RestTemplate();
		Employee emp = temp.getForObject("http://localhost:8080/api/emp/1", Employee.class);
		
		return "FirstName ="+emp.getFname();
	}
	
	@GetMapping("/api/emp/ja2oa")
	public String jsonArrayToObjArray() {
		
		RestTemplate rt = new RestTemplate();
		Employee emps[] = rt.getForObject("http://localhost:8080/api/emp/list", Employee[].class);
		
		return "FullName="+emps[1].getFname()+emps[1].getLname();
	}
	
	@GetMapping("/api/emp/load")
	public String loadProducts() {
		
		RestTemplate rt = new RestTemplate();
		Product products[] = rt.getForObject("https://fakestoreapi.com/products", Product[].class);
		
		prodRepo.saveAll(List.of(products));
		
		return "success";
	}
}