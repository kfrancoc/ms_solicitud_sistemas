package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;


import lombok.Data;

@Data
public class inBusquedaOpcion {
    String impBusqueda;
    String idSistema;
    String nivel;
    String idModulo;
    Integer estadoOpcion;
    Integer cantReg;
    Integer numPag;

}


