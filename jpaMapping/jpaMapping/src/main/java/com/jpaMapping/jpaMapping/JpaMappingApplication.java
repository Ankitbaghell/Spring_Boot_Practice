package com.jpaMapping.jpaMapping;

import com.jpaMapping.jpaMapping.models.Address;
import com.jpaMapping.jpaMapping.models.Laptop;
import com.jpaMapping.jpaMapping.models.Project;
import com.jpaMapping.jpaMapping.models.Student;
import com.jpaMapping.jpaMapping.repository.RdbmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaMappingApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(JpaMappingApplication.class, args);
	}

	@Autowired
	private RdbmsRepository rdbmsRepository;

	@Override
	public void run(String... args) throws Exception {
		Student student1 = new Student();
		student1.setId(1);
		student1.setName("Virat");

		Student student2 = new Student();
		student2.setId(2);
		student2.setName("Rohit");

		Laptop laptop = new Laptop();
		laptop.setLaptop_id(1);
		laptop.setLaptop_name("hp");
		laptop.setStudent(student1);

		student1.setLaptop(laptop);

		Address address = new Address();
		address.setAddress_id(1);
		address.setCity("Bhopal");

		Address address2 = new Address();
		address2.setAddress_id(2);
		address2.setCity("Mumbai");

		address.setStudent(student1);
		address2.setStudent(student1);

		List<Address> addressList = List.of(address, address2);
		student1.setAddresses(addressList);


		Project project1 = new Project();
		project1.setProject_id(1);
		project1.setProject_name("TRS");

		Project project2 = new Project();
		project2.setProject_id(2);
		project2.setProject_name("OMS");

		List<Project> student1Projects = student1.getProjects();
		student1Projects.add(project1);
		student1Projects.add(project2);

		List<Project> student2Projects = student2.getProjects();
		student2Projects.add(project2);
		student2Projects.add(project1);

		rdbmsRepository.save(student1);
		rdbmsRepository.save(student2);

		//-----------------

		Student student = rdbmsRepository.findById(1).get();

	}
}
