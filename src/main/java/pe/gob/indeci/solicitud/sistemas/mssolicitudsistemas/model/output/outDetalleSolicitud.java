package pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.output;

import lombok.Data;

import java.util.Date;

@Data
public class outDetalleSolicitud {

    Long idSolicitud;
    Long idSolDetalle;
    Long idResponsable;
    String descSistema;
    String descSolicitud;
    String descAprobacion;
    String ObsSolicitud;
    String dniPersSolicito;

    String fechApertura;
    String fechAprobacion;

    String fechCreaUsuario;
    Character estadoDetalle;






}
