package pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.input;

import lombok.Data;

@Data
public class inPersona {

    Long idtipodocumento;
    String numerodocumento;
    String apellidos;
    String nombre;
    Character idtiposexo;
    String correoinsitucional;
    String correopersonal;
    String telefono;
    Long idpais;
    String codigodistrito;
    Long idinstitucion;
    Long idoficina;
    Long idcondicionlaboral;
    Long internoexterno;


}
