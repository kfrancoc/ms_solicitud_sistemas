package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services;


import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPerfilModulo;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outPerfilModulo;

import java.util.List;


public interface PerfilServices {

    outBusquedaPerfil listarPerfil(inBusquedaPerfil inbusp);
    List<outPerfil> crearPerfil(outPerfil op);
    List<outPerfil>deshabilitarPerfil(Integer id_perfil);
    List<outPerfil>habilitarPerfil(Integer id_perfil);
    List<outPerfil>detallePerfil(Integer id_perfil);
    List<outPerfilModulo> crearPerfilModulo(outPerfilModulo opm);
    outBusquedaPerfil listarPerfilModulo(inBusquedaPerfilModulo inbuspm);
    List<outPerfilModulo>deshabilitarPerfilModulo(Integer id_perfil_modulo);
}
