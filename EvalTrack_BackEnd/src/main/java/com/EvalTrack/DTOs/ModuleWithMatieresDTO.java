package com.EvalTrack.DTOs;

import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Entities.Module;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ModuleWithMatieresDTO {
    private Module module;
    private List<Matiére> matieres;

    // Constructeur par défaut nécessaire pour Jackson
    public ModuleWithMatieresDTO() {}

    // Constructeur avec annotations JsonCreator/JsonProperty
    @JsonCreator
    public ModuleWithMatieresDTO(
            @JsonProperty("module") Module module,
            @JsonProperty("matieres") List<Matiére> matieres) {
        this.module = module;
        this.matieres = matieres;
    }

    // Getters et setters
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Matiére> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<Matiére> matieres) {
        this.matieres = matieres;
    }
}