package com.EvalTrack.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.EvalTrack.Entities.Matiére;

@Repository
public interface MatiereRepository extends JpaRepository<Matiére, Integer> {
    Matiére findByNom(String nom);
    List<Matiére> findByEnseignantEnseignantId(Integer enseignantId);
    List<Matiére> findByModule_IdModule(Integer idModule);
}
