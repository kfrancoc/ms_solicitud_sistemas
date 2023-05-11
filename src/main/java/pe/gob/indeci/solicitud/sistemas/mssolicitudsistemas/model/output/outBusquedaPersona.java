package pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.model.output;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class outBusquedaPersona {

    String mensaje;
    List<HashMap<String, Object>> lista;
}
