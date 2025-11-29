package com.EvalTrack.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.EvalTrack.Entities.Administrateur;
import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Reclamation;
import com.EvalTrack.Services.AdministrateurService;
import com.EvalTrack.Services.EmailService;
import com.EvalTrack.Services.EtudiantService;
import com.EvalTrack.Services.VerificationCodeService;

@RestController
@RequestMapping("/etudiant")
@CrossOrigin(origins = "http://localhost:8080")
public class EtudiantController {
	private final EtudiantService EtdService;
	private final EmailService emailService;
	private final VerificationCodeService verificationCodeService;
	private final AdministrateurService adminService;
	@Autowired
	public EtudiantController(EtudiantService EtdService, EmailService emailService,VerificationCodeService verificationCodeService, AdministrateurService adminService)
	{
		this.EtdService=EtdService;
		this.emailService =emailService;
		this.verificationCodeService=verificationCodeService;
		this.adminService=adminService;
	}
	
	@PostMapping("/CreateEtudiant")
	@ResponseBody
	public ResponseEntity<Etudiant> createEudiant(@RequestBody Etudiant ETD)
	{
		System.out.println(ETD.getCin());
		Etudiant createEtudiant = EtdService.createEtudiant(ETD);
		if(createEtudiant!=null)
		{
			 return ResponseEntity.ok(createEtudiant);
			
		}else
		{
			 return ResponseEntity.status(400).body(new Etudiant());
		}
	}
	
	@PostMapping("/LoginEtudiant")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> loginEtudiant(@RequestBody Etudiant etudiant) {
	    Map<String, Object> result = EtdService.login(etudiant.getEmail(), etudiant.getMotDePasse());

	    if (result != null) {
	        result.put("message", "Connexion réussie !");
	        return ResponseEntity.ok(result);
	    } else {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Email ou mot de passe incorrect.");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	    }
	}
	 @PutMapping("/{id}")
	 public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant newData) {
		 
		 return ResponseEntity.ok(EtdService.modifierEtudiant(id, newData)) ;
	 }
	 @GetMapping
	    public List<Etudiant> getAllStudents() {
	        return EtdService.getAllStudents();
	    }
	 @GetMapping("/{id}")
	    public Optional<Etudiant> getStudent(@PathVariable("id") Long id) {
	        return EtdService.getEtudiantById(id);
	    }
	 
	 @GetMapping("/bySectionAndNiveau/{sectionId}/{niveau}")
	 public ResponseEntity<List<Etudiant>> getEtudiantsBySectionAndNiveau(@PathVariable("sectionId") Long sectionId, @PathVariable("niveau") int niveau) {
	     List<Etudiant> etudiants = EtdService.getEtudiantsBySectionAndNiveau(sectionId, niveau);
	     return ResponseEntity.ok(etudiants);
	 }

	 @GetMapping("/send-verification-code/{email}")
	 public ResponseEntity<?> sendCode(@PathVariable("email") String email) {
	     String cleanedEmail = email.trim().toLowerCase();

	     boolean emailExists =
	         EtdService.getEtudiantByEmail(cleanedEmail).isPresent() ||
	         adminService.getAdminByEmail(cleanedEmail).isPresent();

	     if (!emailExists) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	     }

	     String code = generateRandomCode();
	     verificationCodeService.storeCode(cleanedEmail, code);
	     emailService.sendVerificationCode(cleanedEmail, code);

	     return ResponseEntity.ok("code envoyé"); // Renvoie 200 sans message
	 }

	 
	 @GetMapping("/verify-code/{email}/{code}")
	 public ResponseEntity<?> verifyCode(@PathVariable("email") String email,@PathVariable("code") String code) {
	     if (!verificationCodeService.verifyCode(email,code)) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code incorrect");
	     }
         if( EtdService.getEtudiantByEmail(email).isPresent()) {
        	 
        	 emailService.sendNewPassword(email, EtdService.updatePassword(email));
         }else
         {
        	 emailService.sendNewPassword(email, adminService.updatePassword(email));
         }
	     return ResponseEntity.ok("Nouveau mot de passe envoyé");
	 }


	 private String generateRandomCode() {
		    Random random = new Random();
		    int code = 100000 + random.nextInt(900000); // génère un nombre entre 100000 et 999999
		    return String.valueOf(code);
		}
	 @PutMapping("/update-password")
	 public ResponseEntity<String> updatePassword(
			 @RequestParam(name = "email") String email,
		        @RequestParam(name = "oldPassword") String oldPassword,
		        @RequestParam(name = "newPassword") String newPassword,
		        @RequestParam(name = "idRole") String idRole) {

	     try {
	    	 if("1".equals(idRole))
	    	 {
	    		 System.out.println("admin");
	    		 adminService.updatePassword(newPassword, email, oldPassword); 
	    	 }
	    	 else if("2".equals(idRole))
	    	 {
	    		 System.out.println("etudiant");
	    		  EtdService.updatePassword(newPassword, email, oldPassword); 
	    	 }
	       
	         return ResponseEntity.ok("Mot de passe mis à jour avec succès.");
	     } catch (IllegalArgumentException e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour du mot de passe.");
	     }
	 }
	 
	 @PostMapping("/reclamation")
	 public Reclamation addReclamation(@RequestParam Long etudiantId, @RequestBody Reclamation reclamation) {
	     return EtdService.addReclamation(etudiantId, reclamation);
	 }

	 @GetMapping("/{idUser}/{idRole}")
	    public ResponseEntity<?> getUser(@PathVariable("idUser") Long idUser, @PathVariable("idRole") int idRole) {
	        Optional<?> user = EtdService.getUserByIdAndRole(idUser, idRole);

	        if (user.isPresent()) {
	            return ResponseEntity.ok(user.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
	        }
	    }
	
}
