package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;

@Data
public class inNivelModulo {

    Long idModulo;
    Long idSistema;
    String descripcion;
    String abrevModulo;
    Long nivel;
    Long idModuloPadre;
    Long estadoModulo;

}
