package com.student.student_service.service;


import com.student.student_service.entity.Student;
import com.student.student_service.repository.StudentRepository;
import com.student.student_service.request.CreateStudentRequest;
import com.student.student_service.response.AddressResponse;
import com.student.student_service.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	WebClient webClient;



	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {


		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddress_id(createStudentRequest.getAddressId());
		student = studentRepository.save(student);

		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(getAddressById(student.getAddress_id()));
		return studentResponse;
	}
	
	public StudentResponse getById (long id) {

		Student student = studentRepository.findById(id).get();
		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(getAddressById(student.getAddress_id()));
		return studentResponse;
	}

	/*** Call webclient to bind the uri ***/

	public AddressResponse getAddressById(long addressId){
		Mono<AddressResponse> addressResponseMono = webClient.get().uri("/get/"+addressId)
				.retrieve().bodyToMono(AddressResponse.class);
		return addressResponseMono.block();
	}
}
