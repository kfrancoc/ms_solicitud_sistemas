package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository;

import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBuscarResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outListaPaginada;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outSistemaSimple;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outUsuarioOficina;

import java.util.List;


@Repository
public interface ResponsableRepository {

    outListaPaginada listarResponsable(inBuscarResponsable insb);

    outListaPaginada listarUsuarioResponsable(inBuscarResponsable insb);


    Integer creaResponsable(inResponsable is);

    String deshabilitarResponsable(Integer cod);

    List<outSistemaSimple> listarSistemaResponsable(Integer cod);

    List<outUsuarioOficina> listaUsuarioOficina(inResponsable insb);

    outListaPaginada listaResponsableSistema(inBuscarResponsable insb);
}
