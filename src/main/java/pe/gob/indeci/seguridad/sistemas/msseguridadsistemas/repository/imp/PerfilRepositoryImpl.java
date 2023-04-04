package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.imp;

import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPerfilModulo;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outModulo;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outPerfil;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outPerfilModulo;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.PerfilRepository;

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
public class PerfilRepositoryImpl implements PerfilRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public outBusquedaPerfil listarPerfil(inBusquedaPerfil inbusp){

        outBusquedaPerfil obp = new outBusquedaPerfil();
        List<HashMap<String,Object>> result = new ArrayList<>();

        if (inbusp.getImpBusqueda() == null){
            inbusp.setImpBusqueda("");
        }
        if(inbusp.getSistema() == null){
            inbusp.setSistema("");
        }
        if(inbusp.getEstado() == null){
            inbusp.setEstado(2);
        }

        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("SP_LISTAR_PERFIL");
            storedProcedureQuery.registerStoredProcedureParameter("in_par_busqueda", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_sistema",String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_perfil_1",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_cant_reg", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_num_pagina", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_perfil", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje",String.class,ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("out_cant_registro",Integer.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_par_busqueda",inbusp.getImpBusqueda());
            storedProcedureQuery.setParameter("in_cant_reg",inbusp.getCantReg());
            storedProcedureQuery.setParameter("in_sistema",inbusp.getSistema());
            storedProcedureQuery.setParameter("in_estado_perfil_1",inbusp.getEstado());
            storedProcedureQuery.setParameter("in_num_pagina",inbusp.getNumPag());

            Integer can = (Integer)storedProcedureQuery.getOutputParameterValue("out_cant_registro");
            String mensaje  = (String)storedProcedureQuery.getOutputParameterValue("out_mensaje");

            List<Object>ls = storedProcedureQuery.getResultList();

            for (Object item : ls){
                Object[]tuple = (Object[]) item;
                BigDecimal idPerfil = (BigDecimal) tuple[0];
                String descripcionPerfil =(String) tuple[1];
                String abreviaturaPerfil  = (String) tuple[2];
                BigDecimal idSistema  = (BigDecimal) tuple[3];
                String descripcionSistema = (String) tuple[4];
                BigDecimal estado  = (BigDecimal) tuple[5];

                HashMap<String,Object> m = new HashMap<>();
                m.put("idPerfil", idPerfil);
                m.put("descripcionPerfil",descripcionPerfil);
                m.put("abreviaturaPerfil",abreviaturaPerfil);
                m.put("idSistema",idSistema);
                m.put("descripcionSistema", descripcionSistema);
                m.put("estado",estado);

                result.add(m);

                obp.setLista(result);
                obp.setCantidad(can);
                obp.setMensaje(mensaje);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return obp;
    }

    @Override
    public List<outPerfil> crearPerfil(outPerfil op) {

        List<outPerfil> result = new ArrayList<>();

        try {

            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_insertar_perfil");
            storedProcedureQuery.registerStoredProcedureParameter("in_descripcion_perfil", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_abreviatura_perfil", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_perfil", Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_perfil", Result.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_descripcion_perfil",op.getDescripcionPerfil());
            storedProcedureQuery.setParameter("in_abreviatura_perfil",op.getAbreviaturaPerfil());
            storedProcedureQuery.setParameter("in_id_sistema",op.getIdSistema());
            storedProcedureQuery.setParameter("in_estado_perfil",op.getEstadoPerfil());

            List<outPerfil> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idPerfil = (BigDecimal) tuple[0];
                String descripcionPerfil = (String) tuple[1];
                String abreviaturaPerfil = (String) tuple[2];
                BigDecimal idSistema = (BigDecimal) tuple[3];
                BigDecimal estadoPerfil = (BigDecimal) tuple[4];

                outPerfil ops = new outPerfil();
                ops.setIdPerfil(idPerfil.longValue());
                ops.setDescripcionPerfil(descripcionPerfil);
                ops.setAbreviaturaPerfil(abreviaturaPerfil);
                ops.setIdSistema(idSistema.longValue());
                ops.setEstadoPerfil(estadoPerfil.longValue());

                result.add(ops);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public List<outPerfil>deshabilitarPerfil(Integer id_perfil){

        List<outPerfil>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_deshabilitar_perfil");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_perfil", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_perfil", id_perfil);

            List<outPerfil> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idPerfil = (BigDecimal) tuple[0];
                String descripcionPerfil = (String) tuple[1];
                String abreviaturaPerfil = (String) tuple[2];
                BigDecimal idSistema = (BigDecimal) tuple[3];
                BigDecimal estadoPerfil = (BigDecimal) tuple[4];


                outPerfil ops = new outPerfil();
                ops.setIdPerfil(idPerfil.longValue());
                ops.setDescripcionPerfil(descripcionPerfil);
                ops.setAbreviaturaPerfil(abreviaturaPerfil);
                ops.setIdSistema(idSistema.longValue());
                ops.setEstadoPerfil(estadoPerfil.longValue());

                result.add(ops);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<outPerfil>habilitarPerfil(Integer id_perfil){

        List<outPerfil>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_habilitar_perfil");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_perfil", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_perfil", id_perfil);

            List<outPerfil> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idPerfil = (BigDecimal) tuple[0];
                String descripcionPerfil = (String) tuple[1];
                String abreviaturaPerfil = (String) tuple[2];
                BigDecimal idSistema = (BigDecimal) tuple[3];
                BigDecimal estadoPerfil = (BigDecimal) tuple[4];


                outPerfil ops = new outPerfil();
                ops.setIdPerfil(idPerfil.longValue());
                ops.setDescripcionPerfil(descripcionPerfil);
                ops.setAbreviaturaPerfil(abreviaturaPerfil);
                ops.setIdSistema(idSistema.longValue());
                ops.setEstadoPerfil(estadoPerfil.longValue());

                result.add(ops);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<outPerfil>detallePerfil(Integer id_perfil){

        List<outPerfil> result  = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_detalle_perfil");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_perfil", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class,ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_perfil",id_perfil);

            List<outModulo> ls =  storedProcedureQuery.getResultList();

            for (Object item : ls){

                Object[] tuple = (Object[]) item;
                BigDecimal idPerfil = (BigDecimal) tuple[0];
                String descripcionPerfil = (String) tuple[1];
                String abreviaturaPerfil = (String) tuple[2];
                BigDecimal idSistema = (BigDecimal) tuple[3];
                BigDecimal estadoPerfil = (BigDecimal) tuple[4];

                outPerfil ops = new outPerfil();
                ops.setIdPerfil(idPerfil.longValue());
                ops.setDescripcionPerfil(descripcionPerfil);
                ops.setAbreviaturaPerfil(abreviaturaPerfil);
                ops.setIdSistema(idSistema.longValue());
                ops.setEstadoPerfil(estadoPerfil.longValue());
                result.add(ops);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return  result;
    }

    @Override
    public List<outPerfilModulo> crearPerfilModulo(outPerfilModulo opm) {

        List<outPerfilModulo> result = new ArrayList<>();

        try {

            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_insertar_perfil_modulo");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_perfil", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_modulo", Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_perfil_modulo", Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_perfil_modulo", Result.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_sistema",opm.getIdSistemas());
            storedProcedureQuery.setParameter("in_id_perfil",opm.getIdPerfil());
            storedProcedureQuery.setParameter("in_id_modulo",opm.getIdModulo());
            storedProcedureQuery.setParameter("in_estado_perfil_modulo",opm.getEstadoPerfilModulo());

            List<outPerfilModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idPerfilModulo = (BigDecimal) tuple[0];
                BigDecimal idSistemas = (BigDecimal) tuple[1];
                String sistema = (String) tuple[2];
                BigDecimal idPerfil = (BigDecimal) tuple[3];
                String descripcionPerfil = (String) tuple[4];
                BigDecimal idModulo = (BigDecimal) tuple[5];
                String descripcionModulo = (String) tuple[6];
                BigDecimal nivelModulo = (BigDecimal) tuple[7];
                BigDecimal estadoPerfilModulo = (BigDecimal) tuple[8];
                Date fecCreacion = (Date) tuple[9];
                BigDecimal idusuarioCreado = (BigDecimal) tuple[10];
                Date fecModificacion = (Date) tuple[11];
                BigDecimal idUsuarioModificado = (BigDecimal) tuple[12];

                outPerfilModulo opms = new outPerfilModulo();
                opms.setIdPerfilModulo(idPerfilModulo.longValue());
                opms.setIdSistemas(idSistemas.longValue());
                opms.setSistema(sistema);
                opms.setIdPerfil(idPerfil.longValue());
                opms.setDescripcionPerfil(descripcionPerfil);
                opms.setIdModulo(idModulo.longValue());
                opms.setDescripcionModulo(descripcionModulo);
                opms.setNivelModulo(nivelModulo.longValue());
                opms.setEstadoPerfilModulo(estadoPerfilModulo.longValue());
                opms.setFecCreacion(fecCreacion);
                opms.setIdusuarioCreado(idusuarioCreado.longValue());
                opms.setFecModificacion(fecModificacion);
                opms.setIdUsuarioModificado(idUsuarioModificado.longValue());

                result.add(opms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public outBusquedaPerfil listarPerfilModulo(inBusquedaPerfilModulo inbuspm){

        outBusquedaPerfil obp = new outBusquedaPerfil();
        List<HashMap<String,Object>> result = new ArrayList<>();

        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_insertar_perfil_modulo_listar");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_sistema",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_id_perfil",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_perfil_modulo",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_cant_reg", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_num_pagina", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_perfil_modulo", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje",String.class,ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("out_cant_registro",Integer.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_sistema",inbuspm.getSistema());
            storedProcedureQuery.setParameter("in_id_perfil",inbuspm.getPerfil());
            storedProcedureQuery.setParameter("in_estado_perfil_modulo",inbuspm.getEstado());
            storedProcedureQuery.setParameter("in_cant_reg",inbuspm.getCantReg());
            storedProcedureQuery.setParameter("in_num_pagina",inbuspm.getNumPag());

            Integer can = (Integer)storedProcedureQuery.getOutputParameterValue("out_cant_registro");
            String mensaje  = (String)storedProcedureQuery.getOutputParameterValue("out_mensaje");

            List<Object>ls = storedProcedureQuery.getResultList();

            for (Object item : ls){
                Object[]tuple = (Object[]) item;
                BigDecimal idPerfilModulo = (BigDecimal) tuple[0];
                BigDecimal idSistemas = (BigDecimal) tuple[1];
                String sistema = (String) tuple[2];
                BigDecimal idPerfil = (BigDecimal) tuple[3];
                String descripcionPerfil = (String) tuple[4];
                BigDecimal idModulo = (BigDecimal) tuple[5];
                String descripcionModulo = (String) tuple[6];
                BigDecimal nivelModulo = (BigDecimal) tuple[7];
                String descNivelModulo = (String) tuple[8];
                BigDecimal estadoPerfilModulo = (BigDecimal) tuple[9];
                Date fecCreacion = (Date) tuple[10];
                BigDecimal idusuarioCreado = (BigDecimal) tuple[11];
                Date fecModificacion = (Date) tuple[12];
                BigDecimal idUsuarioModificado = (BigDecimal) tuple[13];

                HashMap<String,Object> m = new HashMap<>();
                m.put("idPerfilModulo", idPerfilModulo);
                m.put("idSistemas",idSistemas);
                m.put("sistema",sistema);
                m.put("idPerfil",idPerfil);
                m.put("descripcionPerfil", descripcionPerfil);
                m.put("idModulo",idModulo);
                m.put("descripcionModulo",descripcionModulo);
                m.put("nivelModulo",nivelModulo);
                m.put("descNivelModulo",descNivelModulo);
                m.put("estadoPerfilModulo",estadoPerfilModulo);
                m.put("fecCreacion",fecCreacion);
                m.put("idusuarioCreado",idusuarioCreado);
                m.put("fecModificacion",fecModificacion);
                m.put("idUsuarioModificado",idUsuarioModificado);

                result.add(m);

                obp.setLista(result);
                obp.setCantidad(can);
                obp.setMensaje(mensaje);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return obp;
    }

    @Override
    public List<outPerfilModulo>deshabilitarPerfilModulo(Integer id_perfil_modulo){

        List<outPerfilModulo>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("sp_deshabilitar_perfil_modulo");
            storedProcedureQuery.registerStoredProcedureParameter("in_id_perfil_modulo", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_id_perfil_modulo", id_perfil_modulo);

            List<outPerfilModulo> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idPerfilModulo = (BigDecimal) tuple[0];
                BigDecimal idSistemas = (BigDecimal) tuple[1];
                String sistema = (String) tuple[2];
                BigDecimal idPerfil = (BigDecimal) tuple[3];
                String descripcionPerfil = (String) tuple[4];
                BigDecimal idModulo = (BigDecimal) tuple[5];
                String descripcionModulo = (String) tuple[6];
                BigDecimal nivelModulo = (BigDecimal) tuple[7];
                String descNivelModulo = (String) tuple[8];
                BigDecimal estadoPerfilModulo = (BigDecimal) tuple[9];
                Date fecCreacion = (Date) tuple[10];
                BigDecimal idusuarioCreado = (BigDecimal) tuple[11];
                Date fecModificacion = (Date) tuple[12];
                BigDecimal idUsuarioModificado = (BigDecimal) tuple[13];


                outPerfilModulo opms = new outPerfilModulo();
                opms.setIdPerfilModulo(idPerfilModulo.longValue());
                opms.setIdSistemas(idSistemas.longValue());
                opms.setSistema(sistema);
                opms.setIdPerfil(idPerfil.longValue());
                opms.setDescripcionPerfil(descripcionPerfil);
                opms.setIdModulo(idModulo.longValue());
                opms.setDescripcionModulo(descripcionModulo);
                opms.setNivelModulo(nivelModulo.longValue());
                opms.setEstadoPerfilModulo(estadoPerfilModulo.longValue());
                opms.setFecCreacion(fecCreacion);
                opms.setIdusuarioCreado(idusuarioCreado.longValue());
                opms.setFecModificacion(fecModificacion);
                opms.setIdUsuarioModificado(idUsuarioModificado.longValue());

                result.add(opms);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }
}
