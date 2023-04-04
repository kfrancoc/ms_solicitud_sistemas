package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBuscarResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outListaPaginada;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outSistemaSimple;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outUsuarioOficina;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.ResponsableRepository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.ResponsableServices;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ResponsableServicesImpl implements ResponsableServices {

    @Autowired
    private ResponsableRepository responsableRepository;

    @Override
    public outListaPaginada listarResponsable(inBuscarResponsable insb) {
        return this.responsableRepository.listarResponsable(insb);
    }

    @Override
    public outListaPaginada listarUsuarioResponsable(inBuscarResponsable insb) {
        return this.responsableRepository.listarUsuarioResponsable(insb);
    }

    @Override
    public Integer creaResponsable(inResponsable is) {
        return this.responsableRepository.creaResponsable(is);
    }

    @Override
    public String deshabilitarResponsable(Integer cod) {
        return this.responsableRepository.deshabilitarResponsable(cod);
    }

    @Override
    public List<outSistemaSimple> listaSistemaResponsable(Integer cod) {
        return this.responsableRepository.listarSistemaResponsable(cod);
    }

    @Override
    public List<outUsuarioOficina> listaUsuarioOficina(inResponsable insb) {
        return this.responsableRepository.listaUsuarioOficina(insb);
    }

    @Override
    public outListaPaginada listarResponsableSistema(inBuscarResponsable insb) {
        return this.responsableRepository.listaResponsableSistema(insb);
    }


}
