package com.EvalTrack.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.EvalTrack.Entities.Examen;


public interface ExamenRepository extends JpaRepository<Examen, Integer> {
 
	@Query("SELECT e FROM Examen e WHERE e.etudiant.id = :etudiantId")
    List<Examen> findByEtudiantId(@Param("etudiantId") int etudiantId);
	
	@Query("SELECT e FROM Examen e WHERE e.matiere.id = :matiereId")
    List<Examen> findByMatiereId(@Param("matiereId") int matiereId);
	
	List<Examen> findByTypeExam(String type);
	
	Examen findByEtudiantIdAndMatiere_MatiereIdAndTypeExamAndSession(Long etudiantId,int matiereId,String type,String session);
}
