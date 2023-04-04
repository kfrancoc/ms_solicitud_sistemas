package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output;

import lombok.Data;

@Data
public class outModulo {
    Long idModulo;
    Long idSistema;
    String sistema;
    String descripcionModulo;
    String abrevModulo;
    String urlPermiso;
    Long nivel;
    String descripcionNivel;
    Long idModuloPadre;
    String padre;
    Long estadoModulo;

}

