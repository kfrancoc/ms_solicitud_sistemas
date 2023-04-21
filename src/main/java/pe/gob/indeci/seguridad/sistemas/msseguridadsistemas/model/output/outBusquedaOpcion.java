package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class outBusquedaOpcion {
    String mensaje;
    Integer cantidad;
    List<HashMap<String, Object>> lista;

}