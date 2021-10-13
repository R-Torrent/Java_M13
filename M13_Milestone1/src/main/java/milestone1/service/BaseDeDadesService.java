package milestone1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import milestone1.domain.Empleat;
import milestone1.persistence.EmpleatRepositori;

@Service
public class BaseDeDadesService implements IBaseDeDades {
	
	@Autowired
	EmpleatRepositori bd;
	
	@Override
	public Empleat crear(Empleat empleat) { return bd.save(empleat); }
	
	@Override
	public List<Empleat> llegirTots() {	return bd.findAll(); }
	
	@Override
	public Optional<Empleat> llegir(Long id) { return bd.findById(id); }
	
	@Override
	public Optional<Empleat> modificar(Long id) { return bd.findById(id); }
	
	@Override
	public void esborrar(Long id) { bd.deleteById(id); }
	
}
