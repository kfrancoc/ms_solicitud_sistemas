//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inNivelModulo2;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inNivelSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inPerfilOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outOpcion;

@Repository
public interface OpcionesRepository {
    List<inNivelSistema> listarNivelSistema(Integer id_sistema);

    List<inNivelModulo2> listarNivelModulo(Integer id_sistema, Integer id_nivel);

    List<outOpcion> crearOpcion(outOpcion oo);

    outBusquedaOpcion listarOpcion(inBusquedaOpcion inbo);

    List<inPerfilOpcion> listarPerfilOpcion(Integer id_perfil, Integer id_modulo);
}