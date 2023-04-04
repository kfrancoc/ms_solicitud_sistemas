package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.ModuloRepository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.ModuloServices;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ModuloServicesImpl implements ModuloServices {

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public List<outNivelModulo> listarNivelModulo(){
        return this.moduloRepository.listarNivelModulo();

    }

    @Override
    public outBusquedaSistema listarModulo(inBusquedaSistema inbus){
        return this.moduloRepository.listarModulo(inbus);
    }

    @Override
    public List<inNivelSistema>listarNivelSistema(Integer id_sistema){
        return  this.moduloRepository.listarNivelSistema(id_sistema);
    }

    @Override
    public List<inNivelModulo>listarNivelModulo(Integer id_sistema, Integer id_nivel){
        return  this.moduloRepository.listarNivelModulo(id_sistema,id_nivel);
    }

    @Override
    public List<outModulo> crearModulo(outModulo om){
        return this.moduloRepository.crearModulo(om);
    }

    @Override
    public  List<outModulo> detalleModulo(Integer id_modulo){
        return  this.moduloRepository.detalleModulo(id_modulo);
    }

    @Override
    public List<outModulo>deshabilitarModulo(Integer id_modulo){
        return  this.moduloRepository.deshabilitarModulo(id_modulo);
    }
    @Override
    public List<outModulo>habilitarModulo(Integer id_modulo){
        return  this.moduloRepository.habilitarModulo(id_modulo);
    }

    @Override
    public  List<outModuloUpdate>updateModulo(outModuloUpdate om){
        return this.moduloRepository.updateModulo(om);
    }

    @Override
    public outBusquedaModuloHijo listarModuloHijo(inBusquedaModuloHijo inbusmh){
        return this.moduloRepository.listarModuloHijo(inbusmh);
    }

    @Override
    public List<inNivelModuloSimple>listarNivelModuloSimple(Integer id_sistema, Integer id_modulo){
        return this.moduloRepository.listarNivelModuloSimple(id_sistema,id_modulo);
    }

    @Override
    public List<inNivelModuloSimple>listarNivelModuloSimpleEditar(Integer id_sistema, Integer id_modulo){
        return this.moduloRepository.listarNivelModuloSimpleEditar(id_sistema,id_modulo);
    }

    @Override
    public List<inNivelModulo>listarNivelModulo2(Integer id_sistema, Integer id_modulo, Integer id_nivel){
        return this.moduloRepository.listarNivelModulo2(id_sistema,id_modulo,id_nivel);
    }

    @Override
    public List<inNivelModulo>listarNivelModulo2Editar(Integer id_sistema, Integer id_modulo, Integer id_nivel){
        return this.moduloRepository.listarNivelModulo2Editar(id_sistema,id_modulo,id_nivel);
    }

    @Override
    public List<outModulo> crearModuloHijo(outModulo om){
        return this.moduloRepository.crearModuloHijo(om);
    }

    @Override
    public List<outModulo>eliminarModulo(Integer id_modulo){
        return  this.moduloRepository.eliminarModulo(id_modulo);
    }

    /*@Override
    public  List<outBusquedaPersona> busquedaPersona(String numero_documento){
        return  this.moduloRepository.busquedaPersona(numero_documento);
    }*/

    @Override
    public outBusquedaPersona busquedaPersona(inBusquedaPersona inbusp){
        return this.moduloRepository.busquedaPersona(inbusp);
    }

}
