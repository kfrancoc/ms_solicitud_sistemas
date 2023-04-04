package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output;

import lombok.Data;

import java.util.Date;

@Data
public class outPerfilModulo {
    Long idPerfilModulo;
    Long idSistemas;
    String sistema;
    Long idPerfil;
    String descripcionPerfil;
    Long idModulo;
    String descripcionModulo;
    Long nivelModulo;
    String descNivelModulo;
    Long estadoPerfilModulo;
    Date fecCreacion;
    Long idusuarioCreado;
    Date fecModificacion;
    Long idUsuarioModificado;
}
