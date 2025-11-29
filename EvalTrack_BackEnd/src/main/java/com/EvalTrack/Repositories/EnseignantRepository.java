package com.EvalTrack.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EvalTrack.Entities.Enseignant;


public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

	 Optional<Enseignant> findByEmail(String email);
     
	 List<Enseignant> findAll();
}
