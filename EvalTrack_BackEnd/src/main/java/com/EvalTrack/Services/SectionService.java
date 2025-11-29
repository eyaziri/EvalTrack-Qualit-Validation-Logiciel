package com.EvalTrack.Services;

import com.EvalTrack.Entities.Section;
import com.EvalTrack.Repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Optional<Section> getSectionById(int id) {
        return sectionRepository.findById(id);
    }

    public Section addSection(Section section) {
        return sectionRepository.save(section);
    }

    public Section updateSection(int id, Section updatedSection) {
        return sectionRepository.findById(id).map(section -> {
            section.setNomSection(updatedSection.getNomSection());
          
            return sectionRepository.save(section);
        }).orElse(null);
    }
    public Section updateSemestre(int id, Section updatedSection) {
        return sectionRepository.findById(id).map(section -> {
            section.setNomSection(section.getNomSection());
            return sectionRepository.save(section);
        }).orElse(null);
    }

    public void deleteSection(int id) {
        sectionRepository.deleteById(id);
    }
}