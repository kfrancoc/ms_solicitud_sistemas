package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.imp;

import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSistemaAcces;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSistemas;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outObtModPadre;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outSistemaSimple;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.SistemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class SistemaRepositoryImpl implements SistemaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String crearSistema (inSistemas is) {

        String result = "";

        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("SP_INSERTAR_SISTEMA");
                storedProcedureQuery.registerStoredProcedureParameter("IN_DESCRIPCION", String.class, ParameterMode.IN);
                storedProcedureQuery.registerStoredProcedureParameter("IN_SIGLAS", String.class, ParameterMode.IN);
                storedProcedureQuery.registerStoredProcedureParameter("IN_URL", String.class, ParameterMode.IN);
                //storedProcedureQuery.registerStoredProcedureParameter("IN_USUARIO_CREADO", Long.class, ParameterMode.IN);
                //storedProcedureQuery.registerStoredProcedureParameter("IN_ID_USUARIO_MODIFICADO", Long.class, ParameterMode.IN);
                storedProcedureQuery.registerStoredProcedureParameter("IN_FECHA_PUBLICACION", String.class,ParameterMode.IN);
                //storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", Result.class, ParameterMode.REF_CURSOR);
                storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class, ParameterMode.OUT);


            storedProcedureQuery.setParameter("IN_DESCRIPCION", is.getDescSistemas());
            storedProcedureQuery.setParameter("IN_SIGLAS", is.getSiglas());
            storedProcedureQuery.setParameter("IN_URL", is.getUrl());
           // storedProcedureQuery.setParameter("IN_USUARIO_CREADO", is.getIdUsuarioCreado());
            //storedProcedureQuery.setParameter("IN_ID_USUARIO_MODIFICADO", is.getIdUsuarioCreado());
            storedProcedureQuery.setParameter("IN_FECHA_PUBLICACION", is.getFecPublicacion());

            result = (String) storedProcedureQuery.getOutputParameterValue("OUT_MENSAJE");

          //  List<inSistemas> ls = storedProcedureQuery.getResultList();
/*
            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idSistema = (BigDecimal) tuple[0];
                String descripcion = (String) tuple[1];
                BigDecimal estadoSistema = (BigDecimal) tuple[2];
                Date fecCreacion = (Date) tuple[3];
                Date fecMod = (Date) tuple[4];
                BigDecimal idUsuMod = (BigDecimal) tuple[5];
                BigDecimal idUsuCreado = (BigDecimal) tuple[6];
                String siglas = (String) tuple[7];
                String url = (String) tuple[8];
                String fecPub = (String)tuple[9];


                inSistemas ins = new inSistemas();
                ins.setId(idSistema.longValue());
                ins.setDescSistemas(descripcion);
                ins.setSiglas(siglas);
                ins.setEstadoSistema(estadoSistema.longValue());
                ins.setUrl(url);
                ins.setFecCreacion(fecCreacion);
                ins.setIdUsuarioCreado(idUsuCreado.longValue());
                ins.setFecModificacion(fecMod);
                ins.setIdUsuMod(idUsuMod.longValue());
                ins.setFecPublicacion(fecPub);

                result.add(ins);
            }
            */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }


    @Override
    public List<inSistemas>detalleSistema(Integer cod){

        List<inSistemas> result  = new ArrayList<>();
         try {
              StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("SP_DETALLE_SISTEMA");
              storedProcedureQuery.registerStoredProcedureParameter("IN_CODIGO", Integer.class, ParameterMode.IN);
              storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class,ParameterMode.REF_CURSOR);
              storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class, ParameterMode.OUT);

              storedProcedureQuery.setParameter("IN_CODIGO",cod);

              List<inSistemas> ls =  storedProcedureQuery.getResultList();

              for (Object item : ls){

                  Object[] tuple = (Object[]) item;
                  BigDecimal idSistema = (BigDecimal) tuple[0];
                  String descripcion = (String) tuple[1];
                  BigDecimal estadoSistema = (BigDecimal) tuple[2];
                  Date fecCreacion = (Date) tuple[3];
                  Date fecMod = (Date) tuple[4];
                  BigDecimal idUsuMod = (BigDecimal) tuple[5];
                  BigDecimal idUsuCreado = (BigDecimal) tuple[6];
                  String siglas = (String) tuple[7];
                  String url = (String) tuple[8];
                  String fecPub = (String)tuple[9];


                  inSistemas ins = new inSistemas();
                  ins.setId(idSistema.longValue());
                  ins.setDescSistemas(descripcion);
                  ins.setSiglas(siglas);
                  ins.setEstadoSistema(estadoSistema.longValue());
                  ins.setUrl(url);
                  ins.setFecCreacion(fecCreacion);
                  ins.setIdUsuarioCreado(idUsuCreado.longValue());
                  ins.setFecModificacion(fecMod);
                  ins.setIdUsuMod(idUsuMod.longValue());
                  ins.setFecPublicacion(fecPub);

                  result.add(ins);
              }
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             em.close();
         }

        return  result;
    }


    @Override
    public List<inSistemas>updateSistema(inSistemas is){
        List<inSistemas> result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("SP_EDITAR_SISTEMA");
            storedProcedureQuery.registerStoredProcedureParameter("IN_CODIGO", Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_DESCRIPCION", String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_SIGLAS", String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_ESTADO_SISTEMA",Long.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_URL", String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("IN_CODIGO", is.getId());
            storedProcedureQuery.setParameter("IN_DESCRIPCION", is.getDescSistemas());
            storedProcedureQuery.setParameter("IN_SIGLAS", is.getSiglas());
            storedProcedureQuery.setParameter("IN_ESTADO_SISTEMA", is.getEstadoSistema());
            storedProcedureQuery.setParameter("IN_URL", is.getUrl());

            List<inSistemas> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idSistema = (BigDecimal) tuple[0];
                String descripcion = (String) tuple[1];
                BigDecimal estadoSistema = (BigDecimal) tuple[2];
                Date fecCreacion = (Date) tuple[3];
                Date fecMod = (Date) tuple[4];
                BigDecimal idUsuMod = (BigDecimal) tuple[5];
                BigDecimal idUsuCreado = (BigDecimal) tuple[6];
                String siglas = (String) tuple[7];
                String url = (String) tuple[8];
                String fecPub = (String)tuple[9];


                inSistemas ins = new inSistemas();
                ins.setId(idSistema.longValue());
                ins.setDescSistemas(descripcion);
                ins.setSiglas(siglas);
                ins.setEstadoSistema(estadoSistema.longValue());
                ins.setUrl(url);
                ins.setFecCreacion(fecCreacion);
                ins.setIdUsuarioCreado(idUsuCreado.longValue());
                ins.setFecModificacion(fecMod);
                ins.setIdUsuMod(idUsuMod.longValue());
                ins.setFecPublicacion(fecPub);

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
    public List<inSistemas>eliminarSistema(Integer cod){

        List<inSistemas>result = new ArrayList<>();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("SP_ELIMINAR_SISTEMA");
            storedProcedureQuery.registerStoredProcedureParameter("IN_CODIGO", Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("IN_CODIGO", cod);

            List<inSistemas> ls = storedProcedureQuery.getResultList();

            for (Object item : ls) {

                Object[] tuple = (Object[]) item;
                BigDecimal idSistema = (BigDecimal) tuple[0];
                String descripcion = (String) tuple[1];
                BigDecimal estadoSistema = (BigDecimal) tuple[2];
                Date fecCreacion = (Date) tuple[3];
                Date fecMod = (Date) tuple[4];
                BigDecimal idUsuMod = (BigDecimal) tuple[5];
                BigDecimal idUsuCreado = (BigDecimal) tuple[6];
                String siglas = (String) tuple[7];
                String url = (String) tuple[8];
                String fecPub = (String) tuple[9];


                inSistemas ins = new inSistemas();
                ins.setId(idSistema.longValue());
                ins.setDescSistemas(descripcion);
                ins.setSiglas(siglas);
                ins.setEstadoSistema(estadoSistema.longValue());
                ins.setUrl(url);
                ins.setFecCreacion(fecCreacion);
                ins.setIdUsuarioCreado(idUsuCreado.longValue());
                ins.setFecModificacion(fecMod);
                ins.setIdUsuMod(idUsuMod.longValue());
                ins.setFecPublicacion(fecPub);

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
    public outBusquedaSistema listarSistema(inBusquedaSistema inbs){
            outBusquedaSistema outBusquedaSistema =  new outBusquedaSistema();
            List<HashMap<String,Object>> result = new ArrayList<>();

            if (inbs.getImpBusqueda() == null){
                inbs.setImpBusqueda("");
            }
            if (inbs.getEstado() == null){
                inbs.setEstado(2);
            }

        try {
            StoredProcedureQuery storedProcedureQuery =  em.createStoredProcedureQuery("sp_list_sistema");
            storedProcedureQuery.registerStoredProcedureParameter("in_par_busqueda",String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_cant_reg",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_num_pagina",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("in_estado_sistema",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("out_list_sistema", ResultSet.class,ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("out_mensaje",String.class,ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("out_cant_registro",Integer.class,ParameterMode.OUT);

            storedProcedureQuery.setParameter("in_par_busqueda", inbs.getImpBusqueda());
            storedProcedureQuery.setParameter("in_cant_reg",inbs.getCantReg());
            storedProcedureQuery.setParameter("in_num_pagina", inbs.getNumPag());
            storedProcedureQuery.setParameter("in_estado_sistema", inbs.getEstado());

            Integer cant = (Integer) storedProcedureQuery.getOutputParameterValue("out_cant_registro");
            String mensaje = (String) storedProcedureQuery.getOutputParameterValue("out_mensaje");

            List<Object> ls = storedProcedureQuery.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;
                BigDecimal  cod = (BigDecimal) tuple[0];
                String descripcion = (String) tuple[1];
                String siglas = (String) tuple[2];
                String url = (String) tuple[3];
                String fecPub = (String) tuple[4];
                BigDecimal estado = (BigDecimal) tuple[5];

                HashMap<String,Object>m =new HashMap<>();
                m.put("cod",cod);
                m.put("descripcion",descripcion);
                m.put("siglas",siglas);
                m.put("url",url);
                m.put("fecPub",fecPub);
                m.put("estado",estado);

                result.add(m);
            }

            outBusquedaSistema.setLista(result);
            outBusquedaSistema.setCantidad(cant);
            outBusquedaSistema.setMensaje(mensaje);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return outBusquedaSistema;
    }

    @Override
    public List<outSistemaSimple> listarSistemaSimple(){

        List<outSistemaSimple> result = new ArrayList<>();

        try {
            StoredProcedureQuery storedProcedureQuery =  em.createStoredProcedureQuery("SP_LISTAR_SISTEMA_SIMPLE");
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            List<outSistemaSimple> ls = storedProcedureQuery.getResultList();

            for (Object item : ls){

                Object[] tuple = (Object[]) item;
                BigDecimal idSistema = (BigDecimal) tuple[0];
                String descripcion  = (String) tuple[1];
                BigDecimal estado = (BigDecimal) tuple[2];

                outSistemaSimple os = new outSistemaSimple();
                os.setId(idSistema.longValue());
                os.setDescripcion(descripcion);
                os.setEstado(estado.longValue());

                result.add(os);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return result;
    }

    @Override
    public String insertSistemaAcceso(inSistemaAcces insis){
        String result = "";
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("SP_INSERTAR_SISTEMA_ACCESO");
            storedProcedureQuery.registerStoredProcedureParameter("IN_ID_SISTEMA",Integer.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_USERNAME",String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_PC_ACCES",String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_NOMBRE_PC_ACCES",String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_USU_PC_ACCES",String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("IN_IP_PUBLICA",String.class,ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            String ip = insis.getIpPcAcces();
            InetAddress localhost = InetAddress.getByName(ip);
            String hostname  = localhost.getHostName();
            insis.setNombrePcAcces(hostname);

            storedProcedureQuery.setParameter("IN_ID_SISTEMA",insis.getIdSistema());
            storedProcedureQuery.setParameter("IN_USERNAME",insis.getUsername());
            storedProcedureQuery.setParameter("IN_PC_ACCES",insis.getIpPcAcces());
            storedProcedureQuery.setParameter("IN_NOMBRE_PC_ACCES",insis.getNombrePcAcces());
            storedProcedureQuery.setParameter("IN_USU_PC_ACCES",insis.getUsuarioPcAcces());
            storedProcedureQuery.setParameter("IN_IP_PUBLICA",insis.getIpPublica());

            storedProcedureQuery.execute();

            result = (String)storedProcedureQuery.getOutputParameterValue("OUT_MENSAJE");

        }catch (Exception e){
             e.printStackTrace();
        }finally {
            em.close();
        }
        return  result;
    }

    @Override
    public outObtModPadre sistemaEnlace(){
        outObtModPadre o =  new outObtModPadre();
        List<HashMap<String,Object>> result  = new ArrayList<>();

        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("SP_LISTAR_SISTEMAS_ENLACE");
            storedProcedureQuery.registerStoredProcedureParameter("OUT_LISTA", ResultSet.class,ParameterMode.REF_CURSOR);

            List<outObtModPadre>ls = storedProcedureQuery.getResultList();

            for(Object item: ls){
                Object[]tuple = (Object[]) item;
                BigDecimal estadoSistema = (BigDecimal) tuple[0];
                BigDecimal idSistema = (BigDecimal) tuple[1];
                String imagen = (String) tuple[2];
                String descripcion = (String) tuple[3];
                String url = (String) tuple[4];

                HashMap<String,Object>oo = new HashMap<>();
                oo.put("estadoSistema", estadoSistema.longValue());
                oo.put("idSistema", idSistema.longValue());
                oo.put("imagen", imagen);
                oo.put("descripcion", descripcion);
                oo.put("url",url);
                result.add(oo);
                o.setLista(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return  o;
    }

}
