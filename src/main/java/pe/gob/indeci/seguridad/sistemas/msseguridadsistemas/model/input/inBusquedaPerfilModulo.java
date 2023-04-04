package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;

@Data
public class inBusquedaPerfilModulo {
    Integer sistema;
    Integer perfil;
    Integer estado;
    Integer cantReg;
    Integer numPag;
}

