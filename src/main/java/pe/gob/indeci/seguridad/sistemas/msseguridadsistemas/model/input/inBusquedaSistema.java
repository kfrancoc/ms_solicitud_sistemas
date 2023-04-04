package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;

@Data
public class inBusquedaSistema {

    String impBusqueda;
    String sistema;
    String nivel;
    Integer estado;
    Integer cantReg;
    Integer numPag;
}
