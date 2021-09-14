package com.mukesh.kb.specialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.mukesh.kb.entity.Specialization;
import com.mukesh.kb.repo.SpecializationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SpecializationRepoTest {
	
	@Autowired
	private SpecializationRepository repo;
	
	// use all test cases
	
	@Test
	@Order(1)
	public void specCreateTest() {
		Specialization spec = new Specialization(null,"SDDK","Sajicc","A Serjon");
		spec= repo.save(spec);
		assertNotNull(spec.getId(),"Spec is not created");
	}
	
	@Test
	@Order(2)
	public void specFetch() {
		List<Specialization> list = repo.findAll();
		
		assertNotNull(list);
		assertThat(!list.isEmpty());
	}
	
	

}
