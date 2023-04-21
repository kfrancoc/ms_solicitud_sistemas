package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output;

import lombok.Data;

@Data
public class outOpcion {
    Long idModulo;
    String descripcionOpcion;
    String abreviaturaOpcion;
    Long estadoOpcion;
}