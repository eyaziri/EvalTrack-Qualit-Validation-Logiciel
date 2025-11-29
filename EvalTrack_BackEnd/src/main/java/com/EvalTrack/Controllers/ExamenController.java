package com.EvalTrack.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Examen;
import com.EvalTrack.Repositories.EtudiantRepository;
import com.EvalTrack.Services.ExamenService;

@RestController
@RequestMapping("/examen")
@CrossOrigin(origins = "http://localhost:8080")
public class ExamenController {

	private final ExamenService examService;
	private final EtudiantRepository etudiantRepository ;
	@Autowired
	public ExamenController(ExamenService examService , EtudiantRepository etudiantRepository ) {
		super();
		this.examService = examService;
		this.etudiantRepository= etudiantRepository ;
	}
	
	@GetMapping
    public List<Examen> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public Examen getExamById(@PathVariable int id) {
        return examService.getExamById(id);
    }
    @GetMapping("/getByStudent/{id}")
    public List <Examen> getExamByStudentId(@PathVariable("id") int id) {
        return examService.findByStudent(id);
    }
    @GetMapping("/getBySubject/{id}")
    public List <Examen> getExamBySubjectId(@PathVariable("id") int id) {
        return examService.findBySubject(id);
    }
    @GetMapping("/getByType/{type}")
    public List <Examen> getExamByType(@PathVariable String type) {
        return examService.findByType(type);
    }
    @PostMapping
    public ResponseEntity<?> createExam(@RequestBody Examen examen) {
        Examen savedExam = examService.saveExam(examen);
        
        if (savedExam != null) {
            return ResponseEntity.ok(examen); // 200 OK avec l'objet
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }





    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable int id) {
        examService.deleteExam(id);
    }
    @PutMapping("/{id}")
    public Examen updateExam(@PathVariable int id, @RequestBody Examen updatedExam) {
        return examService.updateExam(id, updatedExam);
    }
    @PutMapping("/update-note/{cinEtudiant}/{idMatiere}/{typeExam}/{note}/{session}")
    public ResponseEntity<Examen> updateNote(
            @PathVariable("cinEtudiant") int cinEtudiant,
            @PathVariable("idMatiere") int idMatiere,
            @PathVariable("typeExam") String typeExam,
            @PathVariable("note") Double note,
            @PathVariable("session") String session) {
        
        Examen examen = examService.updateNote(cinEtudiant, idMatiere, typeExam, note, session);
        
        if (examen != null) {
            return ResponseEntity.ok(examen); // 200 OK avec l'objet
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }


	
}
