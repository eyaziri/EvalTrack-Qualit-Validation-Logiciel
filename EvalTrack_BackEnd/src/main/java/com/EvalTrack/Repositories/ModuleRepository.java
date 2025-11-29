package com.EvalTrack.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EvalTrack.Entities.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

    Optional<Module> findByNomModule(String nomModule);
    List<Module> findBySection_SectionIdAndSemestre(Long idModule, Long semstre);

    List<Module> findAll();
}