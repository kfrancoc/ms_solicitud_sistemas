package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository;

import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPersona;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inPersona;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSolicitud;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.*;

import java.util.List;

@Repository
public interface SolicitudRepository {
    outBusquedaPersona busquedaPersona(inBusquedaPersona inbusp);

    Integer crearPersona(inPersona is);

    Integer crearSolicitud(inSolicitud is);


    List<outPerfil> listarPerfil(Integer cod);

    List<outListaSimple> listarPaisSimple();

    List<outListaSimple> listarCondicionSimple();

    List<outListaSimple> listarInstitucionTipo(String cod);

    List<outListaSimple> listarSistemaSimple();

    List<outListaSimple> listarOficinaSimple(Integer cod);

    List<outListaSimple> listarUbigeo(String cod);

    Integer estadoSolicitud(inSolicitud is);

    List<outDetalleSolicitud> detalleSolicitud(inSolicitud is);
    List<outListaSimple> listarCiudad(Integer cod);

    outListaPaginada listarCreaSolicitud(inSolicitud insb);

    List<outDetalleSolicitud> detalleCreaSolicitud(Integer cod);

    outListaPaginada listarAprobSolicitud(inSolicitud insb);

    List<outDetalleSolicitud> detalleAprobSolicitud(Integer cod);
}
