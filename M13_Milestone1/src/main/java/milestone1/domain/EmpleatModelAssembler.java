package milestone1.domain;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;

import milestone1.controllers.Controlador;

@Component
public class EmpleatModelAssembler implements RepresentationModelAssembler<Empleat, EntityModel<Empleat>> {
	
	@Override
	public EntityModel<Empleat> toModel(Empleat empleat) {
		return EntityModel.of(empleat,
				linkTo(methodOn(Controlador.class).u(empleat.getId())).withSelfRel(),
				linkTo(methodOn(Controlador.class).tots()).withRel("empleats"));
	}
	
}
