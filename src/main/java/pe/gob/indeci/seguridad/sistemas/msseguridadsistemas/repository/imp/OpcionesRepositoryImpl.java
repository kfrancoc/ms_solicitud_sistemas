package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.imp;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.xml.transform.Result;
import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inNivelModulo2;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inNivelSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inPerfilOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.OpcionesRepository;

@Repository
public class OpcionesRepositoryImpl implements OpcionesRepository {
    @PersistenceContext
    private EntityManager em;

    public OpcionesRepositoryImpl() {
    }

    public List<inNivelSistema> listarNivelSistema(Integer id_sistema) {
        List<inNivelSistema> result = new ArrayList();

        try {
            StoredProcedureQuery storedProcedureQuery = this.em.createStoredProcedureQuery("SP_LISTAR_NIVEL_MODULO3");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter("in_id_sistema", id_sistema);
            List<inNivelSistema> ls = storedProcedureQuery.getResultList();
            Iterator var5 = ls.iterator();

            while(var5.hasNext()) {
                Object item = var5.next();
                Object[] tuple = (Object[])item;
                BigDecimal nivel = (BigDecimal)tuple[0];
                String descripcionNivel = (String)tuple[1];
                BigDecimal idSistema = (BigDecimal)tuple[2];
                inNivelSistema inm = new inNivelSistema();
                inm.setNivel(nivel.longValue());
                inm.setDescripcionNivel(descripcionNivel);
                inm.setIdSistema(idSistema.longValue());
                result.add(inm);
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            this.em.close();
        }

        return result;
    }

    public List<inNivelModulo2> listarNivelModulo(Integer id_sistema, Integer id_nivel) {
        List<inNivelModulo2> result = new ArrayList();

        try {
            StoredProcedureQuery storedProcedureQuery = this.em.createStoredProcedureQuery("sp_listar_modulo_opciones");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_nivel", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter("in_id_sistema", id_sistema);
            storedProcedureQuery.setParameter("in_nivel", id_nivel);
            List<inNivelModulo2> ls = storedProcedureQuery.getResultList();
            Iterator var6 = ls.iterator();

            while(var6.hasNext()) {
                Object item = var6.next();
                Object[] tuple = (Object[])item;
                BigDecimal idModulo = (BigDecimal)tuple[0];
                BigDecimal idSistema = (BigDecimal)tuple[1];
                String descripcionSistema = (String)tuple[2];
                String descripcionModulo = (String)tuple[3];
                String abrevModuloModulo = (String)tuple[4];
                BigDecimal nivel = (BigDecimal)tuple[5];
                String descripcionNivel = (String)tuple[6];
                BigDecimal idModuloPadre = (BigDecimal)tuple[7];
                BigDecimal estadoModulo = (BigDecimal)tuple[8];
                inNivelModulo2 inm = new inNivelModulo2();
                inm.setIdModulo(idModulo.longValue());
                inm.setIdSistema(idSistema.longValue());
                inm.setDescripcionSistema(descripcionSistema);
                inm.setDescripcionModulo(descripcionModulo);
                inm.setAbrevModuloModulo(abrevModuloModulo);
                inm.setNivel(nivel.longValue());
                inm.setDescripcionNivel(descripcionNivel);
                inm.setIdModuloPadre(idModuloPadre.longValue());
                inm.setEstadoModulo(estadoModulo.longValue());
                result.add(inm);
            }
        } catch (Exception var22) {
            var22.printStackTrace();
        } finally {
            this.em.close();
        }

        return result;
    }

    public List<outOpcion> crearOpcion(outOpcion oo) {
        List<outOpcion> result = new ArrayList();

        try {
            StoredProcedureQuery storedProcedureQuery = this.em.createStoredProcedureQuery("sp_insertar_opciones");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_descripcion_opcion", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_abreviatura_opcion", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_opcion", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_opciones", Result.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter("in_id_modulo", oo.getIdModulo());
            storedProcedureQuery.setParameter("in_descripcion_opcion", oo.getDescripcionOpcion());
            storedProcedureQuery.setParameter("in_abreviatura_opcion", oo.getAbreviaturaOpcion());
            storedProcedureQuery.setParameter("in_estado_opcion", oo.getEstadoOpcion());
            List<outOpcion> ls = storedProcedureQuery.getResultList();
            Iterator var5 = ls.iterator();

            while(var5.hasNext()) {
                Object item = var5.next();
                Object[] tuple = (Object[])item;
                BigDecimal idModulo = (BigDecimal)tuple[0];
                String descripcionOpcion = (String)tuple[1];
                String abreviaturaOpcion = (String)tuple[2];
                BigDecimal estadoOpcion = (BigDecimal)tuple[3];
                outOpcion oms = new outOpcion();
                oms.setIdModulo(idModulo.longValue());
                oms.setDescripcionOpcion(descripcionOpcion);
                oms.setAbreviaturaOpcion(abreviaturaOpcion);
                oms.setEstadoOpcion(estadoOpcion.longValue());
                result.add(oms);
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            this.em.close();
        }

        return result;
    }

    public outBusquedaOpcion listarOpcion(inBusquedaOpcion inbo) {
        outBusquedaOpcion outBusquedaOpcion = new outBusquedaOpcion();
        List<HashMap<String, Object>> result = new ArrayList();
        if (inbo.getImpBusqueda() == null) {
            inbo.setImpBusqueda("");
        }

        if (inbo.getIdSistema() == null) {
            inbo.setIdSistema("");
        }

        if (inbo.getNivel() == null) {
            inbo.setNivel("");
        }

        if (inbo.getIdModulo() == null) {
            inbo.setIdModulo("");
        }

        if (inbo.getEstadoOpcion() == null) {
            inbo.setEstadoOpcion(2);
        }

        try {
            StoredProcedureQuery storedProcedureQuery = this.em.createStoredProcedureQuery("SP_LISTAR_opciones");
            storedProcedureQuery.registerStoredProcedureParameter("in_par_busqueda", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_sistema", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_nivel", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_cant_reg", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_num_pagina", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_opcion", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_opciones", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("out_cant_registro", Integer.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter("in_par_busqueda", inbo.getImpBusqueda());
            storedProcedureQuery.setParameter("in_cant_reg", inbo.getCantReg());
            storedProcedureQuery.setParameter("in_sistema", inbo.getIdSistema());
            storedProcedureQuery.setParameter("in_nivel", inbo.getNivel());
            storedProcedureQuery.setParameter("in_id_modulo", inbo.getIdModulo());
            storedProcedureQuery.setParameter("in_estado_opcion", inbo.getEstadoOpcion());
            storedProcedureQuery.setParameter("in_num_pagina", inbo.getNumPag());
            Integer can = (Integer)storedProcedureQuery.getOutputParameterValue("out_cant_registro");
            String mensaje = (String)storedProcedureQuery.getOutputParameterValue("out_mensaje");
            List<Object> ls = storedProcedureQuery.getResultList();
            Iterator var8 = ls.iterator();

            while(var8.hasNext()) {
                Object item = var8.next();
                Object[] tuple = (Object[])item;
                BigDecimal idOpcion = (BigDecimal)tuple[0];
                String descripcionOpcion = (String)tuple[1];
                String abreviaturaOpcion = (String)tuple[2];
                BigDecimal idModulo = (BigDecimal)tuple[3];
                String descripcionModulo = (String)tuple[4];
                BigDecimal idSistemas = (BigDecimal)tuple[5];
                String sistema = (String)tuple[6];
                BigDecimal idNivel = (BigDecimal)tuple[7];
                String nivel = (String)tuple[8];
                BigDecimal estadoOpcion = (BigDecimal)tuple[9];
                HashMap<String, Object> m = new HashMap();
                m.put("idOpcion", idOpcion);
                m.put("descripcionOpcion", descripcionOpcion);
                m.put("abreviaturaOpcion", abreviaturaOpcion);
                m.put("idModulo", idModulo);
                m.put("descripcionModulo", descripcionModulo);
                m.put("idSistemas", idSistemas);
                m.put("sistema", sistema);
                m.put("idNivel", idNivel);
                m.put("nivel", nivel);
                m.put("estadoOpcion", estadoOpcion);
                result.add(m);
                outBusquedaOpcion.setLista(result);
                outBusquedaOpcion.setCantidad(can);
                outBusquedaOpcion.setMensaje(mensaje);
            }
        } catch (Exception var25) {
            var25.printStackTrace();
        } finally {
            this.em.close();
        }

        return outBusquedaOpcion;
    }

    public List<inPerfilOpcion> listarPerfilOpcion(Integer id_perfil, Integer id_modulo) {
        List<inPerfilOpcion> result = new ArrayList();

        try {
            StoredProcedureQuery storedProcedureQuery = this.em.createStoredProcedureQuery("sp_listar_perfil_opcion");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_perfil", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_perf_opciones", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter("in_id_perfil", id_perfil);
            storedProcedureQuery.setParameter("in_id_modulo", id_modulo);
            List<inPerfilOpcion> ls = storedProcedureQuery.getResultList();
            Iterator var6 = ls.iterator();

            while(var6.hasNext()) {
                Object item = var6.next();
                Object[] tuple = (Object[])item;
                BigDecimal idPerfilOpcion = (BigDecimal)tuple[0];
                BigDecimal idPerfil = (BigDecimal)tuple[1];
                BigDecimal idOpcion = (BigDecimal)tuple[2];
                BigDecimal estadoPerfilOpcion = (BigDecimal)tuple[3];
                inPerfilOpcion inm = new inPerfilOpcion();
                inm.setIdPerfilOpcion(idPerfilOpcion.longValue());
                inm.setIdPerfil(idPerfil.longValue());
                inm.setIdOpcion(idOpcion.longValue());
                inm.setEstadoPerfilOpcion(estadoPerfilOpcion.longValue());
                result.add(inm);
            }
        } catch (Exception var17) {
            var17.printStackTrace();
        } finally {
            this.em.close();
        }

        return result;
    }
}
