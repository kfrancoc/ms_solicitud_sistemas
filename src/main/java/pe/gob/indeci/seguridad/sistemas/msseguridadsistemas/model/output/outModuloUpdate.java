package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output;

import lombok.Data;

import java.util.Date;

@Data
public class outModuloUpdate {
    Long idModulo;
    Long idSistema;
    String descripcionModulo;
    String abrevModulo;
    String urlPermiso;
    Long nivel;
    Long idModuloPadre;
    Long estadoModulo;
    Date fecCreacion;
    Long idUsuarioCreado;
    Date fecModificacion;
    Long idUsuarioModificado;
}
