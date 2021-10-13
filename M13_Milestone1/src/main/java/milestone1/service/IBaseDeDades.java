package milestone1.service;

import java.util.List;
import java.util.Optional;

import milestone1.domain.Empleat;

public interface IBaseDeDades {
	
	public Empleat crear(Empleat empleat);
	public List<Empleat> llegirTots();
	public Optional<Empleat> llegir(Long id);
	public Optional<Empleat> modificar(Long id);
	public void esborrar(Long id);
	
}
