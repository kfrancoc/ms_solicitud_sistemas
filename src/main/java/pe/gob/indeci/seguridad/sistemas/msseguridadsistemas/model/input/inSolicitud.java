package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;



@Data
public class inSolicitud {

    String inDniPersonaCrea;
    String inDniPersonaSolicitado;
    String inDniPersonaAprobacion;

    String idCadena;
    String desCadena;
    String ruta;
    String nombre;
    String archivo;
    String tamanio;
    String tipo;
    String descripcion;



    //LISTAR
    String impBusqueda;
    Integer cantReg;
    Integer numPag;
    Integer estado;
    String estadodet;
    Integer idResponsable;

    Long idSolicitud;
    Long idSolicitudDetalle;
    Integer idSistema;
    String descAprobacion;
    String observacion;



}
