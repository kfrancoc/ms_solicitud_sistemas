package pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.services;


import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.input.inBusquedaPersona;
import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.input.inPersona;
import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.input.inSolicitud;
import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.output.*;

import java.util.List;

public interface SolicitudServices {

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

    List<outListaSimple> listarSistemaAprob(String cod);


}
