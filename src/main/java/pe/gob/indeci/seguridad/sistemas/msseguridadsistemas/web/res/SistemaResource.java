package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.web.res;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSistemaAcces;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSistemas;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outObtModPadre;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outSistemaSimple;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.SistemasServices;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class SistemaResource {
    @Autowired

    private final SistemasServices sistemasServices;

    public SistemaResource(SistemasServices sistemasServices){

        this.sistemasServices = sistemasServices;

    }
    @PostMapping("/seguridad-sistema/crear-sistema")
    public String crearSistema(@Valid @RequestBody inSistemas is) throws URISyntaxException{
    String result = this.sistemasServices.crearSistema(is);
    return result;
    }

    @GetMapping("/seguridad-sistema/detalle-sistema/{cod}")
    public List<inSistemas>detalleSistema(@PathVariable Integer cod) throws URISyntaxException{
        List<inSistemas> result =this.sistemasServices.detalleSistema(cod);
        return result;

    }

    @PostMapping("/seguridad-sistema/actualizar-sistema")
    public List<inSistemas>updateSistema(@Valid @RequestBody inSistemas is)throws URISyntaxException{
        List<inSistemas> result = this.sistemasServices.updateSistema(is);
        return  result;
    }

    @GetMapping("/seguridad-sistema/eliminar-sistema/{cod}")
    public List<inSistemas>eliminarSistema(@PathVariable Integer cod )  throws URISyntaxException{
        List<inSistemas> result  = this.sistemasServices.eliminarSistema(cod);
        return  result;
    }

    @PostMapping("/seguridad-sistema/listar-sistema")
    public outBusquedaSistema listarSistema(@Valid @RequestBody inBusquedaSistema inbs)throws URISyntaxException{
        outBusquedaSistema result = this.sistemasServices.listarSistema(inbs);
        return  result;
    }

    @GetMapping("/seguridad-sistema/listar-sistema-simple")
    public List<outSistemaSimple>listarSistemaSimple() throws URISyntaxException{
        List<outSistemaSimple>result = this.sistemasServices.listarSistemaSimple();
        return  result;
    }

    @PostMapping("/seguridad-sistema/insertar-sistema-acceso")
    public String insertSistemaAcceso(@Valid @RequestBody inSistemaAcces insis)throws URISyntaxException{
        String result = this.sistemasServices.insertSistemaAcceso(insis);
        return  result;
    }

    @GetMapping("/seguridad-sistema/sistema-enlace")
    public outObtModPadre sistemaEnlace()throws URISyntaxException{
        outObtModPadre result = this.sistemasServices.sistemaEnlace();
        return result;
    }


}
