package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services;

import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBuscarResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outListaPaginada;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outSistemaSimple;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outUsuarioOficina;

import java.util.List;


public interface ResponsableServices {


    outListaPaginada listarResponsable(inBuscarResponsable insb);

    outListaPaginada listarUsuarioResponsable(inBuscarResponsable insb);


    Integer creaResponsable(inResponsable is);

    String deshabilitarResponsable(Integer cod);

    List<outSistemaSimple> listaSistemaResponsable(Integer cod);

    List<outUsuarioOficina> listaUsuarioOficina(inResponsable insb);

    outListaPaginada listarResponsableSistema(inBuscarResponsable insb);
}
