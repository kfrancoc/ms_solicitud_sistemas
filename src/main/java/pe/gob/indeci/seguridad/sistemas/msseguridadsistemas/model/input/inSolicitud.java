package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class inSolicitud {

    String inDniPersonaCrea;
    String inDniPersonaSolicitado;

    String idCadena;
    String desCadena;
    //LISTAR
    String impBusqueda;
    Integer cantReg;
    Integer numPag;
    Integer estado;
    String estadodet;
    Integer idResponsable;

    Long idSolicitud;
    Long idSolicitudDetalle;
    Long idSistema;
    String descAprobacion;
    String observacion;



}
