package com.EvalTrack.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EvalTrack.Entities.Administrateur;
import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Examen;
import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Entities.Reclamation;
import com.EvalTrack.Entities.Role;
import com.EvalTrack.Entities.Section;
import com.EvalTrack.Repositories.EtudiantRepository;
import com.EvalTrack.Repositories.ReclamationRepository;
import com.EvalTrack.Repositories.RoleRepository;
import com.EvalTrack.Repositories.SectionRepository;
import com.EvalTrack.Repositories.UserRepository;
import com.EvalTrack.Security.JwtService;
import com.EvalTrack.Security.PasswordGenerator;

@Service
public class EtudiantService {
	private final EtudiantRepository etudiantRepository ;
	private final SectionRepository sectionRepository; 
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository AdminRepository ;
	  private final ReclamationRepository reclamationRepository;
	
	private final JwtService jwtS;
	@Autowired
	public EtudiantService(EtudiantRepository etudiantRepository, SectionRepository sectionRepository, PasswordEncoder passwordEncode,JwtService jwtS , RoleRepository roleRepository,UserRepository AdminRepository, ReclamationRepository reclamationRepository ) {
		this.etudiantRepository = etudiantRepository;
		this.sectionRepository =sectionRepository;
		this.roleRepository=roleRepository;
		this.passwordEncoder = passwordEncode;
		this.jwtS=jwtS;
		this.AdminRepository= AdminRepository;
		this.reclamationRepository=reclamationRepository;
	}
	
	//pour ceer un etudiant
		public Etudiant createEtudiant(Etudiant ETD)
		{
			String hashedPassword = passwordEncoder.encode(ETD.getMotDePasse());
			ETD.setMotDePasse(hashedPassword);
			 // Récupérer la matière si l'ID est fourni
			System.out.println("sectio n"+ ETD.getSection().getIdSection());
	          if (ETD.getSection() != null) {
	              Section section =sectionRepository.findById(ETD.getSection().getIdSection()).orElse(null);
	              ETD.setSection(section);
	          } else {
	        	  ETD.setSection(null);
	          }
	          if(ETD.getRole()!=null)
	          {
	        	  Role role = roleRepository.findById(ETD.getRole().getIdRole()).orElse(null);
	        	  ETD.setRole(role);
	          }else
	          {
	        	  ETD.setRole(null); 
	          }

			return etudiantRepository.save(ETD);
		}
		
		
		//pour connecter au plateforme 
		public Map<String, Object> login(String email, String password) {
		    Optional<Etudiant> optionalEtudiant = etudiantRepository.findByEmail(email);

		    if (optionalEtudiant.isPresent()) {
		        Etudiant etudiant = optionalEtudiant.get();

		        if (passwordEncoder.matches(password, etudiant.getMotDePasse())) {
		            String token = jwtS.generateToken(email);
		            Long idRole = etudiant.getRole().getIdRole();
		            Long idEtudiant =etudiant.getId();

		            Map<String, Object> result = new HashMap<>();
		            result.put("token", token);
		            result.put("idRole", idRole);
		            result.put("idUser", idEtudiant);
		            return result;
		        }
		    }
		    return null;
		}

		
		public Etudiant modifierEtudiant(Long id, Etudiant newData) {
	        return etudiantRepository.findById(id)
	            .map(etudiant -> {
	               
	                etudiant.setEmail(newData.getEmail());
	                
	                // Vérifier si un nouveau mot de passe est fourni et le crypter
	                if (newData.getMotDePasse() != null && !newData.getMotDePasse().isEmpty()) {
	                    etudiant.setMotDePasse(passwordEncoder.encode(newData.getMotDePasse()));
	                }
	                if (newData.getSection() != null) {
	                    etudiant.setSection(newData.getSection());
	                }
	                etudiant.setNiveau(newData.getNiveau());
	                return etudiantRepository.save(etudiant);
	            })
	            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec Id: " +id));
	    }
		public List<Etudiant> getAllStudents() {
	        return etudiantRepository.findAll();
	    }
		public Optional<Etudiant> getEtudiantById(Long id) {
	        return etudiantRepository.findById(id);
	    }
		public List<Etudiant> getEtudiantsBySectionAndNiveau(Long sectionId, int niveau) {
		    return etudiantRepository.findBySection_SectionIdAndNiveau(sectionId, niveau);
		}
		public Optional<Etudiant> getEtudiantByEmail(String email) {
			
	        return etudiantRepository.findByEmail(email.trim().toLowerCase());
	    }
		public String updatePassword(String email) {
		    Optional<Etudiant> etdOpt = etudiantRepository.findByEmail(email);

		    if (etdOpt.isPresent()) {
		        Etudiant etd = etdOpt.get(); 
		        String newPass = PasswordGenerator.generate();
		        etd.setMotDePasse(passwordEncoder.encode(newPass));
		        etudiantRepository.save(etd);
		        return newPass;
		    } else {
		        throw new RuntimeException("Etudiant introuvable avec l'email : " + email);
		    }
		}
	
		
		public void updatePassword(String newPass, String email, String oldPass) {
		    Optional<Etudiant> etudiant = etudiantRepository.findByEmail(email.trim().toLowerCase());
		    
		    if (etudiant.isPresent()) {
		        Etudiant e = etudiant.get();
		        
		        if (passwordEncoder.matches(oldPass, e.getMotDePasse())) {
		            e.setMotDePasse(passwordEncoder.encode(newPass));
		            etudiantRepository.save(e);
		        }
		    }
		}
		
		public Optional<Etudiant> findEtudiantByCin(Integer Cin)
		{
			return etudiantRepository.findByCin(Cin);
		}
		  // Ajouter une réclamation pour un étudiant
	    public Reclamation addReclamation(Long etudiantId, Reclamation reclamation) {
	        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(etudiantId);
	        if (etudiantOpt.isPresent()) {
	            Etudiant etudiant = etudiantOpt.get();
	            reclamation.setEtudiant(etudiant); // Lier la réclamation à l'étudiant

	            return reclamationRepository.save(reclamation);
	        }
	        return null;  // Si l'étudiant n'existe pas
	    }
		
		public Optional<?> getUserByIdAndRole(Long idUser, int idRole) {
		    if (idRole == 2) { // 1 = étudiant
		        return etudiantRepository.findById(idUser);
		    } else if (idRole == 1) { // 2 = administrateur
		        return AdminRepository.findById(idUser);
		    } else {
		        return Optional.empty(); // rôle inconnu
		    }
		}
		
		
		





		 
}
