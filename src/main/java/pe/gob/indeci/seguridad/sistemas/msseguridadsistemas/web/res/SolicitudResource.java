package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.web.res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPersona;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inPersona;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSolicitud;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.SolicitudServices;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SolicitudResource {

    @Autowired
    private  final SolicitudServices solicitudServices;

    public SolicitudResource(SolicitudServices solicitudServices){

        this.solicitudServices = solicitudServices;
    }

    @PostMapping("/seguridad-solicitud/busqueda-persona")
    public outBusquedaPersona busquedaPersona(@Valid @RequestBody inBusquedaPersona inbusp) throws URISyntaxException{

        outBusquedaPersona result = this.solicitudServices.busquedaPersona(inbusp);
        return result;
    }

    @PostMapping("/seguridad-solicitud/crear-persona")
    public Integer crearPersona(@Valid @RequestBody inPersona is) throws URISyntaxException{

        Integer result = this.solicitudServices.crearPersona(is);
        return result;
    }
    @PostMapping("/seguridad-solicitud/crear-solicitud")
    public Integer crearSolicitud(@Valid @RequestBody inSolicitud is)throws URISyntaxException {

        Integer result = this.solicitudServices.crearSolicitud(is);
        return  result;
    }


    @PostMapping("/seguridad-solicitud/listar-crea-solicitud")
    public outListaPaginada listarCreaSolicitud(@Valid @RequestBody inSolicitud insb)throws URISyntaxException {

        outListaPaginada result = this.solicitudServices.listarCreaSolicitud(insb);
        return  result;
    }


    @GetMapping("/seguridad-solicitud/listar-perfil-simple/{cod}")
    public List<outPerfil> listarPerfil(@PathVariable Integer cod) throws URISyntaxException {

        List<outPerfil> result = this.solicitudServices.listarPerfil(cod);

        return  result;
    }

    @GetMapping("/seguridad-solicitud/listar-pais-simple")
    public List<outListaSimple> listarPaisSimple() throws URISyntaxException {

        List<outListaSimple> result = this.solicitudServices.listarPaisSimple();

        return  result;
    }
    @GetMapping("/seguridad-solicitud/listar-condicion-simple")
    public List<outListaSimple> listarCondicionSimple() throws URISyntaxException {

        List<outListaSimple> result = this.solicitudServices.listarCondicionSimple();

        return  result;
    }
    @GetMapping("/seguridad-solicitud/listar-sistema-simple")
    public List<outListaSimple> listarSistemaSimple() throws URISyntaxException {

        List<outListaSimple> result = this.solicitudServices.listarSistemaSimple();

        return  result;
    }
    @GetMapping("/seguridad-solicitud/listar-institucion-tipo/{cod}")
    public List<outListaSimple> listarInstitucionTipo(@PathVariable String cod) throws URISyntaxException {

        List<outListaSimple> result = this.solicitudServices.listarInstitucionTipo(cod);

        return  result;
    }

    @GetMapping("/seguridad-solicitud/listar-oficina-simple/{cod}")
    public List<outListaSimple> listarOficinaSimple(@PathVariable Integer cod) throws URISyntaxException {

        List<outListaSimple> result = this.solicitudServices.listarOficinaSimple(cod);

        return  result;
    }

    @GetMapping("/seguridad-solicitud/listar-ubigeo/{cod}")
    public List<outListaSimple> listarUbigeo(@PathVariable String cod) throws URISyntaxException {

        List<outListaSimple> result = this.solicitudServices.listarUbigeo(cod);

        return  result;
    }

    @PostMapping("/seguridad-solicitud/estado-solicitud")
    public Integer estadoSolicitud(@Valid @RequestBody inSolicitud is)throws URISyntaxException {

        Integer result = this.solicitudServices.estadoSolicitud(is);
        return  result;
    }

    @GetMapping("/seguridad-solicitud/detalle-solicitud")
    public List<outDetalleSolicitud>detallePais(@Valid @RequestBody inSolicitud is) throws URISyntaxException{
        List<outDetalleSolicitud> result =this.solicitudServices.detalleSolicitud(is);
        return result;
    }

    @GetMapping("/seguridad-solicitud/listar-ciudad/{cod}")
    public List<outListaSimple> listarCiudad(@PathVariable Integer cod) throws URISyntaxException {

        List<outListaSimple> result = this.solicitudServices.listarCiudad(cod);

        return  result;
    }

    @GetMapping("/seguridad-solicitud/detalle-crea-solicitud/{cod}")
    public List<outDetalleSolicitud> detalleCreaSolicitud(@PathVariable Integer cod) throws URISyntaxException {

        List<outDetalleSolicitud> result = this.solicitudServices.detalleCreaSolicitud(cod);

        return  result;
    }
    @PostMapping("/seguridad-solicitud/listar-aprob-solicitud")
    public outListaPaginada listarAprobSolicitud(@Valid @RequestBody inSolicitud insb)throws URISyntaxException {

        outListaPaginada result = this.solicitudServices.listarAprobSolicitud(insb);
        return  result;
    }

}
