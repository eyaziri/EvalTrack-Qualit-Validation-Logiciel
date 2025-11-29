package com.EvalTrack.Entities;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleWithMatieresDTO {
    private Module module;
    private List<Matiére> matieres;

    public ModuleWithMatieresDTO(Module module, List<Matiére> matieres) {
        this.module = module;
        this.matieres = matieres;
    }

}
