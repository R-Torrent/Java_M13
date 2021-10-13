package milestone1.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import milestone1.domain.Empleat;
import milestone1.domain.EmpleatModelAssembler;
import milestone1.service.BaseDeDadesService;

@RestController
public class Controlador {
	
	private final BaseDeDadesService repositori;
	private final EmpleatModelAssembler assembler;
	
	public Controlador(BaseDeDadesService repositori, EmpleatModelAssembler assembler) {
		this.repositori = repositori;
		this.assembler = assembler;
    }
	
	@GetMapping("/empleats")
	public CollectionModel<EntityModel<Empleat>> tots() {
		List<EntityModel<Empleat>> empleats = repositori.llegirTots().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(empleats, linkTo(methodOn(Controlador.class).tots()).withSelfRel());
	}
	
	@GetMapping("/empleats/{id}")
	public EntityModel<Empleat> u(@PathVariable Long id) {
		Empleat empleat = repositori.llegir(id)
				.orElseThrow(() -> new EmpleatNoTrobatException(id));
		
		return assembler.toModel(empleat);
	}
	
	@GetMapping("/empleatsFeina/{feina}")
	public CollectionModel<EntityModel<Empleat>> uns(@PathVariable Empleat.Feines feina) {
		List<EntityModel<Empleat>> empleats = repositori.llegirTots().stream()
				.filter(empleat -> empleat.getFeina() == feina)
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(empleats, linkTo(methodOn(Controlador.class).tots()).withSelfRel());
	}
	
	@PostMapping("/empleats")
	public ResponseEntity<?> nouEmpleat(@RequestBody Empleat nouEmpleat) {
		EntityModel<Empleat> entityModel = assembler.toModel(repositori.crear(nouEmpleat));
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	@PutMapping("/empleats/{id}")
	public ResponseEntity<?> actualitzarEmpleat(@RequestBody Empleat novesDadesEmpleat, @PathVariable Long id) {
		Empleat actualitzatEmpleat = repositori.modificar(id)
				.map(empleat -> {
					if(novesDadesEmpleat.getNom() != null)
						empleat.setNom(novesDadesEmpleat.getNom());
					if(novesDadesEmpleat.getCognom() != null)
						empleat.setCognom(novesDadesEmpleat.getCognom());
					if(novesDadesEmpleat.getFeina().toString() != null)
						empleat.setFeina(novesDadesEmpleat.getFeina());
					return repositori.crear(empleat);
				})
				.orElseGet(() -> {
					novesDadesEmpleat.setId(id);
					return repositori.crear(novesDadesEmpleat);
				});
		
		EntityModel<Empleat> entityModel = assembler.toModel(actualitzatEmpleat);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	@DeleteMapping("/empleats/{id}")
	public ResponseEntity<?> esborrarEmpleat(@PathVariable Long id) {
		repositori.esborrar(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
