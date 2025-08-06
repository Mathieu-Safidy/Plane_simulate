package mg.working.Service;

import mg.working.model.Parametre;
import mg.working.repository.ParametreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametreService {
    private final ParametreRepository parametreRepository;

    public ParametreService(ParametreRepository parametreRepository) {
        this.parametreRepository = parametreRepository;
    }

    public List<Parametre> findAll() {
        return parametreRepository.findAll();
    }

    public Optional<Parametre> findById(String id) {
        return parametreRepository.findById(id);
    }

    public void update(Parametre parametre) {
        parametreRepository.save(parametre);
    }
}
