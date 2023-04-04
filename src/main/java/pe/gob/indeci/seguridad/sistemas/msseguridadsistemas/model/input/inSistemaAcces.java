package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input;

import lombok.Data;

@Data
public class inSistemaAcces {

    Integer idSistema;
    String username;
    String ipPcAcces;
    String nombrePcAcces;
    String usuarioPcAcces;
    String ipPublica;
}
