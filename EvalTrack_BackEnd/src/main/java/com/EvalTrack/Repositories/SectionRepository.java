package com.EvalTrack.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EvalTrack.Entities.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    
    Optional<Section> findByNomSection(String nomSection);

    List<Section> findAll();
}
