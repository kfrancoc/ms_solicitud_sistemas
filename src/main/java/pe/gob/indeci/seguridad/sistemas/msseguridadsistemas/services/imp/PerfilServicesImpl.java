package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPerfilModulo;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outPerfilModulo;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.PerfilRepository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.PerfilServices;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PerfilServicesImpl implements PerfilServices {
    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public outBusquedaPerfil listarPerfil(inBusquedaPerfil inbusp){
        return this.perfilRepository.listarPerfil(inbusp);
    }
    @Override
    public List<outPerfil> crearPerfil(outPerfil op){
        return this.perfilRepository.crearPerfil(op);
    }
    @Override
    public List<outPerfil>deshabilitarPerfil(Integer id_perfil){
        return  this.perfilRepository.deshabilitarPerfil(id_perfil);
    }
    @Override
    public List<outPerfil>habilitarPerfil(Integer id_perfil){
        return  this.perfilRepository.habilitarPerfil(id_perfil);
    }
    @Override
    public  List<outPerfil> detallePerfil(Integer id_perfil){
        return  this.perfilRepository.detallePerfil(id_perfil);
    }
    @Override
    public List<outPerfilModulo> crearPerfilModulo(outPerfilModulo opm){
        return this.perfilRepository.crearPerfilModulo(opm);
    }
    @Override
    public outBusquedaPerfil listarPerfilModulo(inBusquedaPerfilModulo inbuspm){
        return this.perfilRepository.listarPerfilModulo(inbuspm);
    }
    @Override
    public List<outPerfilModulo>deshabilitarPerfilModulo(Integer id_perfil_modulo){
        return  this.perfilRepository.deshabilitarPerfilModulo(id_perfil_modulo);
    }
}
