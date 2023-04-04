package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.imp;


import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.ModuloRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class ModuloRepositoryImpl implements ModuloRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<outNivelModulo> listarNivelModulo(){

        List<outNivelModulo> result = new ArrayList<>();

        try {
            StoredProcedureQuery storedProcedureQuery =  em.createStoredProcedureQuery("SP_LISTAR_NIVEL_MODULO");
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            List<outNivelModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls){

            Object[] tuple = (Object[]) item;
            BigDecimal idModulo = (BigDecimal) tuple[0];
            String descripcion  = (String) tuple[1];

            outNivelModulo ons = new outNivelModulo();
            ons.setId(idModulo.longValue());
            ons.setDescripcion(descripcion);

            result.add(ons);
            }

        }catch (Exception e){
         e.printStackTrace();
        }finally {
            em.close();
        }

        return result;
    }

    @Override
    public outBusquedaSistema listarModulo(inBusquedaSistema insb){

        outBusquedaSistema outBusquedaSistema = new outBusquedaSistema();
        List<HashMap<String,Object>>result = new ArrayList<>();

        if (insb.getImpBusqueda() == null){
            insb.setImpBusqueda("");
        }
        if(insb.getSistema() == null){
            insb.setSistema("");
        }

        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("SP_LISTAR_MODULO");
            storedProcedureQuery.registerStoredProcedureParameter("in_par_busqueda", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_sistema",String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_cant_reg", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_num_pagina", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_sistema", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje",String.class,ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("out_cant_registro",Integer.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_par_busqueda",insb.getImpBusqueda());
            storedProcedureQuery.setParameter("in_cant_reg",insb.getCantReg());
            storedProcedureQuery.setParameter("in_sistema",insb.getSistema());
            storedProcedureQuery.setParameter("in_num_pagina",insb.getNumPag());

            Integer can = (Integer)storedProcedureQuery.getOutputParameterValue("out_cant_registro");
            String mensaje  = (String)storedProcedureQuery.getOutputParameterValue("out_mensaje");

            List<Object>ls = storedProcedureQuery.getResultList();

            for (Object item : ls){
                Object[]tuple = (Object[]) item;
                BigDecimal id = (BigDecimal) tuple[0];
                String descModulo =(String) tuple[1];
                String abrevMod  = (String) tuple[2];
                String sistema = (String) tuple[3];
                String nivel  = (String) tuple[4];

                HashMap<String,Object> m = new HashMap<>();
                m.put("id", id);
                m.put("descModulo",descModulo);
                m.put("abrevMod",abrevMod);
                m.put("sistema", sistema);
                m.put("nivel",nivel);

                result.add(m);

                outBusquedaSistema.setLista(result);
                outBusquedaSistema.setCantidad(can);
                outBusquedaSistema.setMensaje(mensaje);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return outBusquedaSistema;
    }

    @Override
    public List<inNivelSistema>listarNivelSistema(Integer id_sistema){

        List<inNivelSistema>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("SP_LISTAR_NIVEL_MODULO2");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_sistema", id_sistema);

            List<inNivelSistema> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal nivel = (BigDecimal) tuple[0];
                String descripcionNivel = (String) tuple[1];
                BigDecimal idSistema = (BigDecimal) tuple[2];


                inNivelSistema inm = new inNivelSistema();
                inm.setNivel(nivel.longValue());
                inm.setDescripcionNivel(descripcionNivel);
                inm.setIdSistema(idSistema.longValue());

                result.add(inm);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<inNivelModulo>listarNivelModulo(Integer id_sistema, Integer id_nivel){

        List<inNivelModulo>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_listar_modulo2");
            storedProcedureQuery.registerStoredProcedureParameter("IN_ID_SISTEMA", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_ID_NIVEL", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("IN_ID_SISTEMA", id_sistema);
            storedProcedureQuery.setParameter("IN_ID_NIVEL", id_nivel);

            List<inNivelModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String descripcion = (String) tuple[2];
                String abrevModulo = (String) tuple[3];
                BigDecimal nivel = (BigDecimal) tuple[4];
                BigDecimal idModuloPadre = (BigDecimal) tuple[5];
                BigDecimal estadoModulo = (BigDecimal) tuple[6];

                inNivelModulo inm = new inNivelModulo();
                inm.setIdModulo(idModulo.longValue());
                inm.setIdSistema(idSistema.longValue());
                inm.setDescripcion(descripcion);
                inm.setAbrevModulo(abrevModulo);
                inm.setNivel(nivel.longValue());
                inm.setIdModuloPadre(idModuloPadre.longValue());
                inm.setEstadoModulo(estadoModulo.longValue());

                result.add(inm);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<outModulo> crearModulo(outModulo om) {

        List<outModulo> result = new ArrayList<>();

        try {

            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_insertar_modulo_n1");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_descripcion_modulo", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_abreviatura_modulo", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_url_permiso", String.class,ParameterMode.IN);
            /*storedProcedureQuery.registerStoredProcedureParameter("in_nivel", Long.class,ParameterMode.IN);
            /storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo_padre", Long.class,ParameterMode.IN);*/
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_modulo", Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_modulo", Result.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_sistema",om.getIdSistema());
            storedProcedureQuery.setParameter("in_descripcion_modulo",om.getDescripcionModulo());
            storedProcedureQuery.setParameter("in_abreviatura_modulo",om.getAbrevModulo());
            storedProcedureQuery.setParameter("in_url_permiso", om.getUrlPermiso());
            /*storedProcedureQuery.setParameter("in_nivel",om.getNivel());
            storedProcedureQuery.setParameter("in_id_modulo_padre",om.getIdModuloPadre());*/
            storedProcedureQuery.setParameter("in_estado_modulo",om.getEstadoModulo());

            List<outModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;

                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                //String sistema = (String) tuple[2];
                String descripcionModulo = (String) tuple[2];
                String abrevModulo = (String) tuple[3];
                String urlPermiso = (String) tuple[4];
                BigDecimal nivel = (BigDecimal) tuple[5];
                //String descripcionNivel = (String) tuple[6];
                BigDecimal idModuloPadre = (BigDecimal) tuple[6];
                //String padre = (String) tuple[9];
                BigDecimal estadoModulo = (BigDecimal) tuple[7];


                outModulo oms = new outModulo();
                oms.setIdModulo(idModulo.longValue());
                oms.setIdSistema(idSistema.longValue());
                //oms.setSistema(sistema);
                oms.setDescripcionModulo(descripcionModulo);
                oms.setAbrevModulo(abrevModulo);
                oms.setUrlPermiso(urlPermiso);
                oms.setNivel(nivel.longValue());
                //oms.setDescripcionNivel(descripcionNivel);
                if (idModuloPadre != null){
                oms.setIdModuloPadre(idModuloPadre.longValue());}
                //oms.setPadre(padre);
                oms.setEstadoModulo(estadoModulo.longValue());

                result.add(oms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public List<outModulo>detalleModulo(Integer id_modulo){

        List<outModulo> result  = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_detalle_modulo");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class,ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_modulo",id_modulo);

            List<outModulo> ls =  storedProcedureQuery.getResultList();

            for (Object item : ls){

                Object[] tuple = (Object[]) item;
                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String sistema = (String) tuple[2];
                String descripcionModulo = (String) tuple[3];
                String abrevModulo = (String) tuple[4];
                String urlPermiso = (String) tuple[5];
                BigDecimal nivel = (BigDecimal) tuple[6];
                String descripcionNivel = (String) tuple[7];
                BigDecimal idModuloPadre = (BigDecimal) tuple[8];
                String padre = (String) tuple[9];
                BigDecimal estadoModulo = (BigDecimal) tuple[10];


                outModulo oms = new outModulo();
                oms.setIdModulo(idModulo.longValue());
                oms.setIdSistema(idSistema.longValue());
                oms.setSistema(sistema);
                oms.setDescripcionModulo(descripcionModulo);
                oms.setAbrevModulo(abrevModulo);
                oms.setUrlPermiso(urlPermiso);
                oms.setNivel(nivel.longValue());
                oms.setDescripcionNivel(descripcionNivel);
                oms.setIdModuloPadre(idModuloPadre.longValue());
                oms.setPadre(padre);
                oms.setEstadoModulo(estadoModulo.longValue());
                result.add(oms);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return  result;
    }

    @Override
    public List<outModulo>deshabilitarModulo(Integer id_modulo){

        List<outModulo>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_deshabilitar_modulo");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_modulo", id_modulo);

            List<outModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String descripcionModulo = (String) tuple[2];
                String abrevModulo = (String) tuple[3];
                String urlPermiso = (String) tuple[4];
                BigDecimal nivel = (BigDecimal) tuple[5];
                BigDecimal idModuloPadre = (BigDecimal) tuple[6];
                BigDecimal estadoModulo = (BigDecimal) tuple[7];


                outModulo ins = new outModulo();
                ins.setIdModulo(idModulo.longValue());
                ins.setIdSistema(idSistema.longValue());
                ins.setDescripcionModulo(descripcionModulo);
                ins.setAbrevModulo(abrevModulo);
                ins.setUrlPermiso(urlPermiso);
                ins.setNivel(nivel.longValue());
                if(idModuloPadre != null){
                ins.setIdModuloPadre(idModuloPadre.longValue());}
                ins.setEstadoModulo(estadoModulo.longValue());

                result.add(ins);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<outModulo>habilitarModulo(Integer id_modulo){

        List<outModulo>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_habilitar_modulo");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_modulo", id_modulo);

            List<outModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String descripcionModulo = (String) tuple[2];
                String abrevModulo = (String) tuple[3];
                String urlPermiso = (String) tuple[4];
                BigDecimal nivel = (BigDecimal) tuple[5];
                BigDecimal idModuloPadre = (BigDecimal) tuple[6];
                BigDecimal estadoModulo = (BigDecimal) tuple[7];


                outModulo ins = new outModulo();
                ins.setIdModulo(idModulo.longValue());
                ins.setIdSistema(idSistema.longValue());
                ins.setDescripcionModulo(descripcionModulo);
                ins.setAbrevModulo(abrevModulo);
                ins.setUrlPermiso(urlPermiso);
                ins.setNivel(nivel.longValue());
                if(idModuloPadre != null){
                    ins.setIdModuloPadre(idModuloPadre.longValue());}
                ins.setEstadoModulo(estadoModulo.longValue());

                result.add(ins);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<outModuloUpdate>updateModulo(outModuloUpdate omu){
        List<outModuloUpdate> result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_actualizar_modulo");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_descripcion_modulo", String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_abreviatura_modulo", String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_nivel",Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo_padre", Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_modulo", Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_modulo", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_modulo", omu.getIdModulo());
            storedProcedureQuery.setParameter("in_id_sistema", omu.getIdSistema());
            storedProcedureQuery.setParameter("in_descripcion_modulo", omu.getDescripcionModulo());
            storedProcedureQuery.setParameter("in_abreviatura_modulo", omu.getAbrevModulo());
            storedProcedureQuery.setParameter("in_nivel", omu.getNivel());
            storedProcedureQuery.setParameter("in_id_modulo_padre", omu.getIdModuloPadre());
            storedProcedureQuery.setParameter("in_estado_modulo", omu.getEstadoModulo());

            List<outModuloUpdate> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String descripcionModulo = (String) tuple[2];
                String abrevModulo = (String) tuple[3];
                BigDecimal nivel = (BigDecimal) tuple[4];
                BigDecimal idModuloPadre = (BigDecimal) tuple[5];
                BigDecimal estadoModulo = (BigDecimal) tuple[6];
                Date fecCreacion = (Date) tuple[7];
                //BigDecimal idUsuarioCreado = (BigDecimal) tuple[8];
                Date fecModificacion = (Date) tuple[8];
                //BigDecimal idUsuarioModificado = (BigDecimal) tuple[10];

                outModuloUpdate oms = new outModuloUpdate();
                oms.setIdModulo(idModulo.longValue());
                oms.setIdSistema(idSistema.longValue());
                oms.setDescripcionModulo(descripcionModulo);
                oms.setAbrevModulo(abrevModulo);
                oms.setNivel(nivel.longValue());
                oms.setIdModuloPadre(idModuloPadre.longValue());
                oms.setEstadoModulo(estadoModulo.longValue());
                oms.setFecCreacion(fecCreacion);
                //oms.setIdUsuarioCreado(idUsuarioCreado.longValue());
                oms.setFecModificacion(fecModificacion);
                //oms.setIdUsuarioModificado(idUsuarioModificado.longValue());

                result.add(oms);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public outBusquedaModuloHijo listarModuloHijo(inBusquedaModuloHijo inbusmh){

        outBusquedaModuloHijo obmh = new outBusquedaModuloHijo();
        List<HashMap<String,Object>> result = new ArrayList<>();

        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_listar_modulo_hijos");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_modulo",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_cant_reg", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_num_pagina", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_modulo_hijos", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje",String.class,ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("out_cant_registro",Integer.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_sistema",inbusmh.getSistema());
            storedProcedureQuery.setParameter("in_id_modulo",inbusmh.getModulo());
            storedProcedureQuery.setParameter("in_estado_modulo",inbusmh.getEstado());
            storedProcedureQuery.setParameter("in_cant_reg",inbusmh.getCantReg());
            storedProcedureQuery.setParameter("in_num_pagina",inbusmh.getNumPag());

            Integer can = (Integer)storedProcedureQuery.getOutputParameterValue("out_cant_registro");
            String mensaje  = (String)storedProcedureQuery.getOutputParameterValue("out_mensaje");

            List<Object>ls = storedProcedureQuery.getResultList();

            for (Object item : ls){
                Object[]tuple = (Object[]) item;
                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistemas = (BigDecimal) tuple[1];
                String sistema = (String) tuple[2];
                String descripcionModulo = (String) tuple[3];
                String abreviaturaModulo = (String) tuple[4];
                String urlPermiso = (String) tuple[5];
                BigDecimal idNivel = (BigDecimal) tuple[6];
                String nivelModulo = (String) tuple[7];
                BigDecimal idModuloPadre = (BigDecimal) tuple[8];
                BigDecimal estadoModulo = (BigDecimal) tuple[9];

                HashMap<String,Object> m = new HashMap<>();
                m.put("idModulo", idModulo);
                m.put("idSistemas",idSistemas);
                m.put("sistema",sistema);
                m.put("descripcionModulo",descripcionModulo);
                m.put("abreviaturaModulo",abreviaturaModulo);
                m.put("urlPermiso", urlPermiso);
                m.put("idNivel",idNivel);
                m.put("nivelModulo",nivelModulo);
                m.put("idModuloPadre",idModuloPadre);
                m.put("estadoModulo",estadoModulo);

                result.add(m);

                obmh.setLista(result);
                obmh.setCantidad(can);
                obmh.setMensaje(mensaje);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return obmh;
    }

    @Override
    public List<inNivelModuloSimple>listarNivelModuloSimple(Integer id_sistema, Integer id_modulo){

        List<inNivelModuloSimple>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_listar_nivel_modulo_simple");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_sistema", id_sistema);
            storedProcedureQuery.setParameter("in_id_modulo", id_modulo);

            List<inNivelModuloSimple> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idNivelModulo = (BigDecimal) tuple[0];
                String descripcionNivelModulo = (String) tuple[1];


                inNivelModuloSimple inm = new inNivelModuloSimple();
                inm.setIdNivelModulo(idNivelModulo.longValue());
                inm.setDescripcionNivelModulo(descripcionNivelModulo);
                result.add(inm);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<inNivelModulo>listarNivelModulo2(Integer id_sistema, Integer id_modulo, Integer id_nivel){

        List<inNivelModulo>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_listar_modulo_nivel_2");
            storedProcedureQuery.registerStoredProcedureParameter("IN_ID_SISTEMA", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_ID_MODULO", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_ID_NIVEL", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("IN_ID_SISTEMA", id_sistema);
            storedProcedureQuery.setParameter("IN_ID_MODULO", id_modulo);
            storedProcedureQuery.setParameter("IN_ID_NIVEL", id_nivel);

            List<inNivelModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String descripcion = (String) tuple[2];
                String abrevModulo = (String) tuple[3];
                BigDecimal nivel = (BigDecimal) tuple[4];
                BigDecimal idModuloPadre = (BigDecimal) tuple[5];
                BigDecimal estadoModulo = (BigDecimal) tuple[6];

                inNivelModulo inm = new inNivelModulo();
                inm.setIdModulo(idModulo.longValue());
                inm.setIdSistema(idSistema.longValue());
                inm.setDescripcion(descripcion);
                inm.setAbrevModulo(abrevModulo);
                inm.setNivel(nivel.longValue());
                inm.setIdModuloPadre(idModuloPadre.longValue());
                inm.setEstadoModulo(estadoModulo.longValue());

                result.add(inm);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<outModulo> crearModuloHijo(outModulo om) {

        List<outModulo> result = new ArrayList<>();

        try {

            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_insertar_modulo_n2");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_descripcion_modulo", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_abreviatura_modulo", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_url_permiso", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo_padre", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_modulo", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_modulo", Result.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_sistema", om.getIdSistema());
            storedProcedureQuery.setParameter("in_descripcion_modulo", om.getDescripcionModulo());
            storedProcedureQuery.setParameter("in_abreviatura_modulo", om.getAbrevModulo());
            storedProcedureQuery.setParameter("in_url_permiso", om.getUrlPermiso());
            storedProcedureQuery.setParameter("in_id_modulo_padre", om.getIdModuloPadre());
            storedProcedureQuery.setParameter("in_estado_modulo", om.getEstadoModulo());

            List<outModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;

                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String descripcionModulo = (String) tuple[2];
                String abrevModulo = (String) tuple[3];
                String urlPermiso = (String) tuple[4];
                BigDecimal nivel = (BigDecimal) tuple[5];
                BigDecimal idModuloPadre = (BigDecimal) tuple[6];
                BigDecimal estadoModulo = (BigDecimal) tuple[7];


                outModulo oms = new outModulo();
                oms.setIdModulo(idModulo.longValue());
                oms.setIdSistema(idSistema.longValue());
                oms.setDescripcionModulo(descripcionModulo);
                oms.setAbrevModulo(abrevModulo);
                oms.setUrlPermiso(urlPermiso);
                oms.setNivel(nivel.longValue());
                oms.setIdModuloPadre(idModuloPadre.longValue());
                oms.setEstadoModulo(estadoModulo.longValue());

                result.add(oms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public List<inNivelModulo>listarNivelModulo2Editar(Integer id_sistema, Integer id_modulo, Integer id_nivel){

        List<inNivelModulo>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_listar_modulo_nivel_2_editar");
            storedProcedureQuery.registerStoredProcedureParameter("IN_ID_SISTEMA", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_ID_MODULO", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_ID_NIVEL", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("IN_ID_SISTEMA", id_sistema);
            storedProcedureQuery.setParameter("IN_ID_MODULO", id_modulo);
            storedProcedureQuery.setParameter("IN_ID_NIVEL", id_nivel);

            List<inNivelModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String descripcion = (String) tuple[2];
                String abrevModulo = (String) tuple[3];
                BigDecimal nivel = (BigDecimal) tuple[4];
                BigDecimal idModuloPadre = (BigDecimal) tuple[5];
                BigDecimal estadoModulo = (BigDecimal) tuple[6];

                inNivelModulo inm = new inNivelModulo();
                inm.setIdModulo(idModulo.longValue());
                inm.setIdSistema(idSistema.longValue());
                inm.setDescripcion(descripcion);
                inm.setAbrevModulo(abrevModulo);
                inm.setNivel(nivel.longValue());
                inm.setIdModuloPadre(idModuloPadre.longValue());
                inm.setEstadoModulo(estadoModulo.longValue());

                result.add(inm);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<inNivelModuloSimple>listarNivelModuloSimpleEditar(Integer id_sistema, Integer id_modulo){

        List<inNivelModuloSimple>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_listar_nivel_modulo_simple_editar");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_sistema", id_sistema);
            storedProcedureQuery.setParameter("in_id_modulo", id_modulo);

            List<inNivelModuloSimple> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idNivelModulo = (BigDecimal) tuple[0];
                String descripcionNivelModulo = (String) tuple[1];


                inNivelModuloSimple inm = new inNivelModuloSimple();
                inm.setIdNivelModulo(idNivelModulo.longValue());
                inm.setDescripcionNivelModulo(descripcionNivelModulo);
                result.add(inm);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<outModulo>eliminarModulo(Integer id_modulo){

        List<outModulo>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_eliminar_modulo_hijo");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_modulo", id_modulo);

            List<outModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idModulo = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String descripcionModulo = (String) tuple[2];
                String abrevModulo = (String) tuple[3];
                BigDecimal nivel = (BigDecimal) tuple[4];
                BigDecimal idModuloPadre = (BigDecimal) tuple[4];
                BigDecimal estadoModulo = (BigDecimal) tuple[5];


                outModulo ins = new outModulo();
                ins.setIdModulo(idModulo.longValue());
                ins.setIdSistema(idSistema.longValue());
                ins.setDescripcionModulo(descripcionModulo);
                ins.setAbrevModulo(abrevModulo);
                ins.setNivel(nivel.longValue());
                ins.setIdModuloPadre(idModuloPadre.longValue());
                ins.setEstadoModulo(estadoModulo.longValue());

                result.add(ins);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public outBusquedaPersona busquedaPersona(inBusquedaPersona inbusp){

        outBusquedaPersona obp = new outBusquedaPersona();
        List<HashMap<String,Object>> result = new ArrayList<>();

        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_buscar_persona");
            storedProcedureQuery.registerStoredProcedureParameter("in_nro_documento", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_persona", Result.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_nro_documento",inbusp.getNumeroDocumento());
            String mensaje = (String) storedProcedureQuery.getOutputParameterValue("out_mensaje");

            List<Object>ls = storedProcedureQuery.getResultList();

            for (Object item : ls){
                Object[]tuple = (Object[]) item;
                BigDecimal idTipoDocumento = (BigDecimal) tuple[0];
                String tipoDocumento = (String) tuple[1];
                String numeroDocumento = (String) tuple[2];
                String apellidosPersona = (String) tuple[3];
                String nombresPersona = (String) tuple[4];
                Character idTipoSexo = (Character) tuple[5];
                String correoInstitucional = (String) tuple[6];
                String correoPersonal = (String) tuple[7];
                String numeroTelefono = (String) tuple[8];
                BigDecimal idPais = (BigDecimal) tuple[9];
                String nombrePais = (String) tuple[10];
                BigDecimal idInstitucion = (BigDecimal) tuple[11];
                String nombreInstitucion = (String) tuple[12];
                BigDecimal idOficina = (BigDecimal) tuple[13];
                String nombreOficina = (String) tuple[14];
                BigDecimal idCondicionLaboral = (BigDecimal) tuple[15];
                String condicionLaboral = (String) tuple[16];
                String codigoDistrito = (String) tuple[17];
                String distrito = (String) tuple[18];
                String provincia = (String) tuple[19];
                String departamento = (String) tuple[20];
                BigDecimal internoExterno = (BigDecimal) tuple[21];

                HashMap<String,Object> m = new HashMap<>();
                m.put("idTipoDocumento", idTipoDocumento);
                m.put("tipoDocumento",tipoDocumento);
                m.put("numeroDocumento",numeroDocumento);
                m.put("apellidosPersona",apellidosPersona);
                m.put("nombresPersona",nombresPersona);
                m.put("idTipoSexo", idTipoSexo);
                m.put("correoInstitucional",correoInstitucional);
                m.put("correoPersonal",correoPersonal);
                m.put("numeroTelefono",numeroTelefono);
                m.put("idPais",idPais);
                m.put("nombrePais",nombrePais);
                m.put("idInstitucion",idInstitucion);
                m.put("nombreInstitucion",nombreInstitucion);
                m.put("idOficina",idOficina);
                m.put("nombreOficina",nombreOficina);
                m.put("idCondicionLaboral",idCondicionLaboral);
                m.put("condicionLaboral",condicionLaboral);
                m.put("codigoDistrito",codigoDistrito);
                m.put("distrito",distrito);
                m.put("provincia",provincia);
                m.put("departamento",departamento);
                m.put("internoExterno",internoExterno);

                result.add(m);

                obp.setLista(result);
                obp.setMensaje(mensaje);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return obp;
    }

    /*@Override
    public List<outBusquedaPersona>busquedaPersona(String numero_documento){

        List<outBusquedaPersona> result  = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_buscar_persona");
            storedProcedureQuery.registerStoredProcedureParameter("in_nro_documento", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_persona", Result.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_nro_documento",numero_documento);

            List<outBusquedaPersona> ls =  storedProcedureQuery.getResultList();

            for (Object item : ls){

                Object[] tuple = (Object[]) item;
                BigDecimal idTipoDocumento = (BigDecimal) tuple[0];
                String tipoDocumento = (String) tuple[1];
                String numeroDocumento = (String) tuple[2];
                String apellidosPersona = (String) tuple[3];
                String nombresPersona = (String) tuple[4];
                Character idTipoSexo = (Character) tuple[5];
                String correoInstitucional = (String) tuple[6];
                String correoPersonal = (String) tuple[7];
                String numeroTelefono = (String) tuple[8];
                BigDecimal idPais = (BigDecimal) tuple[9];
                String nombrePais = (String) tuple[10];
                BigDecimal idInstitucion = (BigDecimal) tuple[11];
                String nombreInstitucion = (String) tuple[12];
                BigDecimal idOficina = (BigDecimal) tuple[13];
                String nombreOficina = (String) tuple[14];
                BigDecimal idCondicionLaboral = (BigDecimal) tuple[15];
                String condicionLaboral = (String) tuple[16];
                String codigoDistrito = (String) tuple[17];
                String distrito = (String) tuple[18];
                String provincia = (String) tuple[19];
                String departamento = (String) tuple[20];
                BigDecimal internoExterno = (BigDecimal) tuple[21];


                outBusquedaPersona obps = new outBusquedaPersona();
                obps.setIdTipoDocumento(idTipoDocumento.longValue());
                obps.setTipoDocumento(tipoDocumento);
                obps.setNumeroDocumento(numeroDocumento);
                obps.setApellidosPersona(apellidosPersona);
                obps.setNombresPersona(nombresPersona);
                obps.setIdTipoSexo(idTipoSexo);
                obps.setCorreoInstitucional(correoInstitucional);
                obps.setCorreoPersonal(correoPersonal);
                obps.setNumeroTelefono(numeroTelefono);
                obps.setIdPais(idPais.longValue());
                obps.setNombrePais(nombrePais);
                obps.setIdInstitucion(idInstitucion.longValue());
                obps.setNombreInstitucion(nombreInstitucion);
                obps.setIdOficina(idOficina.longValue());
                obps.setNombreOficina(nombreOficina);
                obps.setIdCondicionLaboral(idCondicionLaboral.longValue());
                obps.setCondicionLaboral(condicionLaboral);
                obps.setCodigoDistrito(codigoDistrito);
                obps.setCodigoDistrito(distrito);
                obps.setProvincia(provincia);
                obps.setDepartamento(departamento);
                obps.setInternoExterno(internoExterno.longValue());

                result.add(obps);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return  result;
    }*/
}
