package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;


import lombok.Data;

import java.util.Date;

@Data
public class inSistemas {

    Long id;
    String descSistemas;
    Long estadoSistema;
    String siglas;
    Date fecCreacion;
    Date fecModificacion;
    String url;
    Long idUsuMod;
    Long idUsuarioCreado;
    String fecPublicacion;

}
