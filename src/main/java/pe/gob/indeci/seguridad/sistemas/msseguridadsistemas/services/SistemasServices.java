package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services;

import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSistemaAcces;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSistemas;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outObtModPadre;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outSistemaSimple;

import java.util.List;

public interface SistemasServices {

String crearSistema (inSistemas is);
List<inSistemas>detalleSistema (Integer cod);
List<inSistemas>updateSistema(inSistemas is);
List<inSistemas>eliminarSistema(Integer cod);
outBusquedaSistema listarSistema(inBusquedaSistema inbs);

List<outSistemaSimple>listarSistemaSimple();

String insertSistemaAcceso(inSistemaAcces insis);
outObtModPadre sistemaEnlace();
}
