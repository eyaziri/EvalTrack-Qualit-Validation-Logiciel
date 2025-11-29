package com.EvalTrack.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EvalTrack.Entities.Enseignant;
import com.EvalTrack.Repositories.EnseignantRepository;
import com.EvalTrack.Security.JwtService;

@Service
public class EnseignantService {
	
	
	private EnseignantRepository enseignantRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtS;
	@Autowired
	public EnseignantService(EnseignantRepository enseignantRepository, PasswordEncoder passwordEncoder,
			JwtService jwtS) {
		super();
		this.enseignantRepository = enseignantRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtS = jwtS;
	}
	
	//pour ceer un enseignant
			public Enseignant createEnseignant(Enseignant es)
			{
				String hashedPassword = passwordEncoder.encode(es.getMotDePasse());
				es.setMotDePasse(hashedPassword);
				return enseignantRepository.save(es);
			}
			public String login(String email, String password)
			{
				if(enseignantRepository.findByEmail(email) != null)
				{
					Enseignant enseignant = enseignantRepository.findByEmail(email).get();
					//on compare le mot de passe fournie par celui haché
					if (enseignant != null && passwordEncoder.matches(password,enseignant.getMotDePasse())) {
			            return jwtS.generateToken(email); // Générer un JWT
			        }
				}
				return null;
			}
			public Enseignant modifierEnseignant(Long id, Enseignant newData) {
		        return enseignantRepository.findById(id)
		            .map(enseignant -> {
		               
		            	enseignant.setEmail(newData.getEmail());
		                
		                // Vérifier si un nouveau mot de passe est fourni et le crypter
		                if (newData.getMotDePasse() != null && !newData.getMotDePasse().isEmpty()) {
		                	enseignant.setMotDePasse(passwordEncoder.encode(newData.getMotDePasse()));
		                }
		              
		               
		                return enseignantRepository.save(enseignant);
		            })
		            .orElseThrow(() -> new RuntimeException("Enseignant non trouvé avec Id: " +id));
		    }
			public List<Enseignant> getAllEnseignants() {
		        return enseignantRepository.findAll();
		    }
			public Optional<Enseignant> getEtudiantById(Long id) {
		        return enseignantRepository.findById(id);
		    }
			 
	
	
	

}
