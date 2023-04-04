package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository;

import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.*;

import java.util.List;

@Repository
public interface ModuloRepository {
    List <outNivelModulo> listarNivelModulo();

    outBusquedaSistema listarModulo(inBusquedaSistema inbus);

    List<inNivelSistema>listarNivelSistema(Integer id_sistema);

    List<inNivelModulo>listarNivelModulo(Integer id_sistema, Integer id_nivel);

    List<outModulo> crearModulo(outModulo om);

    List<outModulo>detalleModulo(Integer id_modulo);

    List<outModulo>deshabilitarModulo(Integer id_modulo);

    List<outModulo>habilitarModulo(Integer id_modulo);

    List<outModuloUpdate>updateModulo(outModuloUpdate om);

    outBusquedaModuloHijo listarModuloHijo(inBusquedaModuloHijo inbusmh);

    List<inNivelModuloSimple> listarNivelModuloSimple(Integer id_sistema, Integer id_modulo);

    List<inNivelModuloSimple> listarNivelModuloSimpleEditar(Integer id_sistema, Integer id_modulo);

    List<inNivelModulo>listarNivelModulo2(Integer id_sistema, Integer id_modulo, Integer id_nivel);

    List<inNivelModulo>listarNivelModulo2Editar(Integer id_sistema, Integer id_modulo, Integer id_nivel);

    List<outModulo> crearModuloHijo(outModulo om);

    List<outModulo>eliminarModulo(Integer id_modulo);

    outBusquedaPersona busquedaPersona(inBusquedaPersona inbusp);
    //List<outBusquedaPersona>busquedaPersona(String numero_documento);

}
