package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output;

import lombok.Data;

import java.util.Date;

@Data
public class outPerfil {
    Long idPerfil;
    String descripcionPerfil;
    String abreviaturaPerfil;
    Long idSistema;
    Long estadoPerfil;
    Date fecCreacion;
    Long idUsuarioCreado;
    Date fecModificacion;
    Long idUsuarioModificado;
}
