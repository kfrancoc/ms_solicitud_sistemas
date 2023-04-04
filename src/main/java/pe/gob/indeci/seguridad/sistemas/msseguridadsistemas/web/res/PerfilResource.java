package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.web.res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPerfilModulo;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outPerfilModulo;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.PerfilServices;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PerfilResource {
    @Autowired

    private final PerfilServices perfilServices;
    public PerfilResource(PerfilServices perfilServices) {
        this.perfilServices = perfilServices;
    }

    @PostMapping("/seguridad-perfil/listar-perfil")
    public outBusquedaPerfil listarPerfil(@Valid @RequestBody inBusquedaPerfil inbsp) throws URISyntaxException {
        outBusquedaPerfil result = this.perfilServices.listarPerfil(inbsp);
        return  result;
    }

    @PostMapping("/seguridad-perfil/crear-perfil")
    public List<outPerfil>crearPerfil(@Valid @RequestBody outPerfil op) throws URISyntaxException{
        List<outPerfil> result =this.perfilServices.crearPerfil(op);
        return result;
    }

    @GetMapping("/seguridad-perfil/deshabilitar-perfil/{id_perfil}")
    public List<outPerfil>deshabilitarPerfil(@PathVariable Integer id_perfil )  throws URISyntaxException{
        List<outPerfil> result  = this.perfilServices.deshabilitarPerfil(id_perfil);
        return  result;
    }

    @GetMapping("/seguridad-perfil/habilitar-perfil/{id_perfil}")
    public List<outPerfil>habilitarPerfil(@PathVariable Integer id_perfil )  throws URISyntaxException{
        List<outPerfil> result  = this.perfilServices.habilitarPerfil(id_perfil);
        return  result;
    }

    @GetMapping("/seguridad-perfil/detalle-perfil/{id_perfil}")
    public List<outPerfil>detallePerfil(@PathVariable Integer id_perfil) throws URISyntaxException{
        List<outPerfil> result =this.perfilServices.detallePerfil(id_perfil);
        return result;
    }

    @PostMapping("/seguridad-perfil/crear-perfil-modulo")
    public List<outPerfilModulo>crearPerfilModulo(@Valid @RequestBody outPerfilModulo opm) throws URISyntaxException{
        List<outPerfilModulo> result =this.perfilServices.crearPerfilModulo(opm);
        return result;
    }

    @PostMapping("/seguridad-perfil/listar-perfil-modulo")
    public outBusquedaPerfil listarPerfilModulo(@Valid @RequestBody inBusquedaPerfilModulo inbspm) throws URISyntaxException {
        outBusquedaPerfil result = this.perfilServices.listarPerfilModulo(inbspm);
        return  result;
    }

    @GetMapping("/seguridad-perfil/deshabilitar-perfil-modulo/{id_perfil_modulo}")
    public List<outPerfilModulo>deshabilitarPerfilModulo(@PathVariable Integer id_perfil_modulo )  throws URISyntaxException{
        List<outPerfilModulo> result  = this.perfilServices.deshabilitarPerfilModulo(id_perfil_modulo);
        return  result;
    }
}
