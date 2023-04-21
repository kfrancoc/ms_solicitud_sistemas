package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;

@Data
public class inNivelModulo2 {
    Long idModulo;
    Long idSistema;
    String descripcionSistema;
    String descripcionModulo;
    String abrevModuloModulo;
    Long nivel;
    String descripcionNivel;
    Long idModuloPadre;
    Long estadoModulo;
}
