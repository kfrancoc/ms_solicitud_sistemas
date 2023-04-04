package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;

@Data
public class inBuscarResponsable {

    Integer estado;
    String impBusqueda;
    Integer cantReg;
    Integer numPag;
    Integer idSistema;
}
