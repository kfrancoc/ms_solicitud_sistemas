package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;

@Data
public class inBusquedaModuloHijo {
    Integer sistema;
    Integer modulo;
    Integer estado;
    Integer cantReg;
    Integer numPag;

}
