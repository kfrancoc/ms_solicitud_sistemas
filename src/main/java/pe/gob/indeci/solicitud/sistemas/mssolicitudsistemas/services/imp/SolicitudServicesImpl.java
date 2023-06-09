package pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.input.inBusquedaPersona;
import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.input.inPersona;
import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.input.inSolicitud;
import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.repository.SolicitudRepository;
import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.services.SolicitudServices;
import pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.output.*;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SolicitudServicesImpl implements SolicitudServices {


    @Autowired
    private SolicitudRepository solicitudRepository;
   // private DetalleArchivoRepository detalleArchivoRepository;

    @Override
    public outBusquedaPersona busquedaPersona(inBusquedaPersona inbusp){
        return this.solicitudRepository.busquedaPersona(inbusp);
    }

    @Override
    public Integer crearPersona(inPersona is) {
        return this.solicitudRepository.crearPersona(is);
    }

    @Override
    public Integer crearSolicitud(inSolicitud is) {
        return this.solicitudRepository.crearSolicitud(is);
    }



    @Override
    public List<outPerfil> listarPerfil(Integer cod) {
        return this.solicitudRepository.listarPerfil(cod);
    }

    @Override
    public List<outListaSimple> listarPaisSimple() {
        return this.solicitudRepository.listarPaisSimple();
    }

    @Override
    public List<outListaSimple> listarCondicionSimple() {
        return this.solicitudRepository.listarCondicionSimple();
    }

    @Override
    public List<outListaSimple> listarInstitucionTipo(String cod) {
        return this.solicitudRepository.listarInstitucionTipo(cod);
    }

    @Override
    public List<outListaSimple> listarSistemaSimple() {
        return this.solicitudRepository.listarSistemaSimple();
    }

    @Override
    public List<outListaSimple> listarOficinaSimple(Integer cod) {
        return this.solicitudRepository.listarOficinaSimple(cod);
    }

    @Override
    public List<outListaSimple> listarUbigeo(String cod) {
        return this.solicitudRepository.listarUbigeo(cod);
    }

    @Override
    public Integer estadoSolicitud(inSolicitud is) {
        return this.solicitudRepository.estadoSolicitud(is);
    }

    @Override
    public List<outDetalleSolicitud> detalleSolicitud(inSolicitud is) {
        return this.solicitudRepository.detalleSolicitud(is);
    }

    @Override
    public List<outListaSimple> listarCiudad(Integer cod) {
        return this.solicitudRepository.listarCiudad(cod);
    }

    @Override
    public outListaPaginada listarCreaSolicitud(inSolicitud insb) {
        return this.solicitudRepository.listarCreaSolicitud(insb);
    }

    @Override
    public List<outDetalleSolicitud> detalleCreaSolicitud(Integer cod) {
        return this.solicitudRepository.detalleCreaSolicitud(cod);
    }

    @Override
    public outListaPaginada listarAprobSolicitud(inSolicitud insb) {
        return this.solicitudRepository.listarAprobSolicitud(insb);
    }

    @Override
    public List<outDetalleSolicitud> detalleAprobSolicitud(Integer cod) {
        return this.solicitudRepository.detalleAprobSolicitud(cod);
    }

    @Override
    public List<outListaSimple> listarSistemaAprob(String cod) {
        return this.solicitudRepository.listarSistemaAprob(cod);
    }


}
