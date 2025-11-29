package com.EvalTrack.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EvalTrack.Entities.Administrateur;

public interface UserRepository extends JpaRepository<Administrateur,Long>  {

	 Optional<Administrateur> findByEmail(String email);
	 List<Administrateur> findAll();
}
