package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.web.res;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.ModuloServices;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ModuloResource {
    @Autowired

    private final ModuloServices moduloServices;


    public ModuloResource(ModuloServices moduloServices) {
        this.moduloServices = moduloServices;
    }
    @GetMapping("/seguridad-modulo/listar-nivel")
    public List<outNivelModulo>listarNivelModulo() throws URISyntaxException{
        List<outNivelModulo>result = this.moduloServices.listarNivelModulo();
        return  result;

    }

    @PostMapping("/seguridad-modulo/listar-modulo")
    public outBusquedaSistema listarModulo(@Valid @RequestBody inBusquedaSistema inbs) throws URISyntaxException{
        outBusquedaSistema result = this.moduloServices.listarModulo(inbs);
        return  result;
    }

    @GetMapping("/seguridad-modulo/listar-nivel-sistema/{id_sistema}")
    public List<inNivelSistema>listarNivelSistema(@PathVariable Integer id_sistema)  throws URISyntaxException{
        List<inNivelSistema> result  = this.moduloServices.listarNivelSistema(id_sistema);
        return  result;
    }

    @GetMapping("/seguridad-modulo/listar-nivel-modulo/{id_sistema}/{id_nivel}")
    public List<inNivelModulo>listarNivelModulo(@PathVariable Integer id_sistema, @PathVariable Integer id_nivel)  throws URISyntaxException{
        List<inNivelModulo> result  = this.moduloServices.listarNivelModulo(id_sistema,id_nivel);
        return  result;
    }

    @PostMapping("/seguridad-modulo/crear-modulo")
    public List<outModulo>crearModulo(@Valid @RequestBody outModulo om) throws URISyntaxException{
        List<outModulo> result =this.moduloServices.crearModulo(om);
        return result;
    }

    @GetMapping("/seguridad-modulo/detalle-modulo/{id_modulo}")
    public List<outModulo>detalleModulo(@PathVariable Integer id_modulo) throws URISyntaxException{
        List<outModulo> result =this.moduloServices.detalleModulo(id_modulo);
        return result;
    }

    @GetMapping("/seguridad-modulo/deshabilitar-modulo/{id_modulo}")
    public List<outModulo>deshabilitarModulo(@PathVariable Integer id_modulo )  throws URISyntaxException{
        List<outModulo> result  = this.moduloServices.deshabilitarModulo(id_modulo);
        return  result;
    }

    @GetMapping("/seguridad-modulo/habilitar-modulo/{id_modulo}")
    public List<outModulo>habilitarModulo(@PathVariable Integer id_modulo )  throws URISyntaxException{
        List<outModulo> result  = this.moduloServices.habilitarModulo(id_modulo);
        return  result;
    }

    @PostMapping("/seguridad-modulo/actualizar-modulo")
    public List<outModuloUpdate>updateModulo(@Valid @RequestBody outModuloUpdate om)throws URISyntaxException{
        List<outModuloUpdate> result = this.moduloServices.updateModulo(om);
        return  result;
    }

    @PostMapping("/seguridad-modulo/listar-modulo-hijo")
    public outBusquedaModuloHijo listarModuloHijo(@Valid @RequestBody inBusquedaModuloHijo inbusmh) throws URISyntaxException {
        outBusquedaModuloHijo result = this.moduloServices.listarModuloHijo(inbusmh);
        return  result;
    }

    @GetMapping("/seguridad-modulo/listar-nivel-modulo-simple/{id_sistema}/{id_modulo}")
    public List<inNivelModuloSimple>listarNivelModuloSimple(@PathVariable Integer id_sistema, @PathVariable Integer id_modulo)  throws URISyntaxException{
        List<inNivelModuloSimple> result  = this.moduloServices.listarNivelModuloSimple(id_sistema,id_modulo);
        return  result;
    }

    @GetMapping("/seguridad-modulo/listar-nivel-modulo-simpleEditar/{id_sistema}/{id_modulo}")
    public List<inNivelModuloSimple>listarNivelModuloSimpleEditar(@PathVariable Integer id_sistema, @PathVariable Integer id_modulo)  throws URISyntaxException{
        List<inNivelModuloSimple> result  = this.moduloServices.listarNivelModuloSimpleEditar(id_sistema,id_modulo);
        return  result;
    }


    @GetMapping("/seguridad-modulo/listar-nivel-modulo2/{id_sistema}/{id_modulo}/{id_nivel}")
    public List<inNivelModulo>listarNivelModulo2(@PathVariable Integer id_sistema, @PathVariable Integer id_modulo,@ PathVariable Integer id_nivel)  throws URISyntaxException{
        List<inNivelModulo> result  = this.moduloServices.listarNivelModulo2(id_sistema,id_modulo,id_nivel);
        return  result;
    }

    @GetMapping("/seguridad-modulo/listar-nivel-modulo2Editar/{id_sistema}/{id_modulo}/{id_nivel}")
    public List<inNivelModulo>listarNivelModulo2Editar(@PathVariable Integer id_sistema, @PathVariable Integer id_modulo,@ PathVariable Integer id_nivel)  throws URISyntaxException{
        List<inNivelModulo> result  = this.moduloServices.listarNivelModulo2Editar(id_sistema,id_modulo,id_nivel);
        return  result;
    }

    @PostMapping("/seguridad-modulo/crear-modulo-hijo")
    public List<outModulo>crearModuloHijo(@Valid @RequestBody outModulo om) throws URISyntaxException{
        List<outModulo> result =this.moduloServices.crearModuloHijo(om);
        return result;
    }

    @GetMapping("/seguridad-modulo/eliminar-modulo/{id_modulo}")
    public List<outModulo>eliminarModulo(@PathVariable Integer id_modulo)  throws URISyntaxException{
        List<outModulo> result  = this.moduloServices.eliminarModulo(id_modulo);
        return  result;
    }

    /*@PostMapping("/seguridad-modulo/busqueda-persona/{numero_documento}")
    public List<outBusquedaPersona>busquedaPersona(@PathVariable String numero_documento) throws URISyntaxException{
        List<outBusquedaPersona> result =this.moduloServices.busquedaPersona(numero_documento);
        return result;
    }*/

    @PostMapping("/seguridad-modulo/busqueda-persona")
    public outBusquedaPersona busquedaPersona(@Valid @RequestBody inBusquedaPersona inbusp) throws URISyntaxException {
        outBusquedaPersona result = this.moduloServices.busquedaPersona(inbusp);
        return  result;
    }
}
