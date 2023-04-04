package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.web.res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBuscarResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outListaPaginada;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outSistemaSimple;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outUsuarioOficina;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.ResponsableServices;


import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ResponsableResource {

    @Autowired
    private final ResponsableServices responsableServices;

    public ResponsableResource(ResponsableServices responsableServices){

        this.responsableServices =  responsableServices;
    }
    @PostMapping("/seguridad-persona/listar-responsable")
    public outListaPaginada listarResponsable(@Valid @RequestBody inBuscarResponsable insb)throws URISyntaxException {

        outListaPaginada result = this.responsableServices.listarResponsable(insb);
        return  result;
    }
    @PostMapping("/seguridad-persona/listar-responsable-sistema")
    public outListaPaginada listarResponsableSistema(@Valid @RequestBody inBuscarResponsable insb)throws URISyntaxException {

        outListaPaginada result = this.responsableServices.listarResponsableSistema(insb);
        return  result;
    }

    @GetMapping("/seguridad-persona/listar-usuario-responsable")
    public outListaPaginada listarUsuarioResponsable(@Valid @RequestBody inBuscarResponsable insb)throws URISyntaxException {

        outListaPaginada result = this.responsableServices.listarUsuarioResponsable(insb);
        return  result;
    }


    @PostMapping("/seguridad-persona/crear-responsable")
    public Integer habilitarPais(@Valid @RequestBody inResponsable is) throws  URISyntaxException{
        Integer result = this.responsableServices.creaResponsable(is);
        return result;

    }

    @GetMapping("/seguridad-persona/deshabilitar-responsable/{cod}")
    public String deshabilitarResponsable(@PathVariable Integer cod) throws  URISyntaxException{


        String result = this.responsableServices.deshabilitarResponsable(cod);

        return result;

    }
    @GetMapping("/seguridad-persona/listar-sistema-responsable/{cod}")
    public List<outSistemaSimple>listaSistemaResponsable(@PathVariable Integer cod) throws URISyntaxException{
        List<outSistemaSimple> result =this.responsableServices.listaSistemaResponsable(cod);
        return result;
    }

    @PostMapping("/seguridad-persona/listar-usuario-oficina")
    public List<outUsuarioOficina>listaUsuarioOficina(@Valid @RequestBody inResponsable insb) throws URISyntaxException{
        List<outUsuarioOficina> result =this.responsableServices.listaUsuarioOficina(insb);
        return result;
    }

}
