package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;

@Data
public class inBusquedaPerfil {
    String impBusqueda;
    String sistema;
    Integer estado;
    Integer cantReg;
    Integer numPag;
}
