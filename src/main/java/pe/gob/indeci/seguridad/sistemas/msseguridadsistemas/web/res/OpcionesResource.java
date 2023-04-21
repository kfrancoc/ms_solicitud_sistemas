package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.web.res;

import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inNivelModulo2;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inNivelSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inPerfilOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.OpcionesServices;

@RestController
@RequestMapping({"/api/v1"})
public class OpcionesResource {
    @Autowired
    private final OpcionesServices opcionesServices;

    public OpcionesResource(OpcionesServices opcionesServices) {
        this.opcionesServices = opcionesServices;
    }

    @GetMapping({"/seguridad-opciones/listar-nivel-sistema/{id_sistema}"})
    public List<inNivelSistema> listarNivelSistema(@PathVariable Integer id_sistema) throws URISyntaxException {
        List<inNivelSistema> result = this.opcionesServices.listarNivelSistema(id_sistema);
        return result;
    }

    @GetMapping({"/seguridad-opciones/listar-nivel-modulo/{id_sistema}/{id_nivel}"})
    public List<inNivelModulo2> listarNivelModulo(@PathVariable Integer id_sistema, @PathVariable Integer id_nivel) throws URISyntaxException {
        List<inNivelModulo2> result = this.opcionesServices.listarNivelModulo(id_sistema, id_nivel);
        return result;
    }

    @PostMapping({"/seguridad-opciones/crear-opcion"})
    public List<outOpcion> crearModulo(@RequestBody @Valid outOpcion om) throws URISyntaxException {
        List<outOpcion> result = this.opcionesServices.crearOpcion(om);
        return result;
    }

    @PostMapping({"/seguridad-opciones/listar-opcion"})
    public outBusquedaOpcion listarOpcion(@RequestBody @Valid inBusquedaOpcion inbo) throws URISyntaxException {
        outBusquedaOpcion result = this.opcionesServices.listarOpcion(inbo);
        return result;
    }

    @GetMapping({"/seguridad-opciones/listar-perfil-opcion/{id_perfil}/{id_modulo}"})
    public List<inPerfilOpcion> listarPerfilOpcion(@PathVariable Integer id_perfil, @PathVariable Integer id_modulo) throws URISyntaxException {
        List<inPerfilOpcion> result = this.opcionesServices.listarPerfilOpcion(id_perfil, id_modulo);
        return result;
    }
}
