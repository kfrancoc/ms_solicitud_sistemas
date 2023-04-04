package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.imp;

import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBuscarResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inResponsable;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outListaPaginada;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outSistemaSimple;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outUsuarioOficina;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.ResponsableRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ResponsableRepositoryImpl implements ResponsableRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public outListaPaginada listarResponsable(inBuscarResponsable insb) {

        outListaPaginada outListaPaginada = new outListaPaginada();
        List<HashMap<String,Object>> result = new ArrayList<>();

        if(insb.getImpBusqueda() == null){
            insb.setImpBusqueda("");
        }
        if(insb.getEstado() == null){
            insb.setEstado(2);
        }
        try{

            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_LISTAR_RESPONSABLE");


            query.registerStoredProcedureParameter("IN_PAR_BUSQUEDA",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CANT_REG", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_NUM_PAGINA", Integer.class,ParameterMode.IN);


            query.registerStoredProcedureParameter("OUT_LISTA", ResultSet.class,ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);
            query.registerStoredProcedureParameter("OUT_CANT_REGISTRO",Integer.class,ParameterMode.OUT);


            query.setParameter("IN_PAR_BUSQUEDA",insb.getImpBusqueda());
            query.setParameter("IN_CANT_REG", insb.getCantReg());
            query.setParameter("IN_NUM_PAGINA",insb.getNumPag());


            Integer cant = (Integer) query.getOutputParameterValue("OUT_CANT_REGISTRO");
            String  msj = (String) query.getOutputParameterValue("OUT_MENSAJE");


            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;
                BigDecimal idReponsable = (BigDecimal) tuple[0];
                BigDecimal idPersona = (BigDecimal) tuple[1];
                String nroDocumento = (String) tuple[2];
                String nomApellidos = (String) tuple[3];
                BigDecimal idSistema = (BigDecimal) tuple[4];
                String descSistema = (String) tuple[5];
                String descOficina = (String) tuple[6];
                BigDecimal estado = (BigDecimal) tuple[7];

                HashMap<String,Object> m = new HashMap<>();

                m.put("idReponsable",idReponsable);
                m.put("idPersona",idPersona);
                m.put("nroDocumento",nroDocumento);
                m.put("nomApellidos",nomApellidos);
                m.put("descOficina",descOficina);
                m.put("idSistema",idSistema);
                m.put("descSistema",descSistema);
                m.put("estado",estado);

                result.add(m);


            }

            outListaPaginada.setLista(result);
            outListaPaginada.setCantidad(cant);
            outListaPaginada.setMensaje(msj);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return outListaPaginada;
    }

    @Override
    public outListaPaginada listarUsuarioResponsable(inBuscarResponsable insb) {

        outListaPaginada outListaPaginada = new outListaPaginada();
        List<HashMap<String,Object>> result = new ArrayList<>();

        if(insb.getImpBusqueda() == null){
            insb.setImpBusqueda("");
        }
        if(insb.getEstado() == null){
            insb.setEstado(2);
        }
        try{

            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_LISTAR_USUARIO_RESPONSABLE");


            query.registerStoredProcedureParameter("IN_PAR_BUSQUEDA",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CANT_REG", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_NUM_PAGINA", Integer.class,ParameterMode.IN);


            query.registerStoredProcedureParameter("OUT_LISTA", ResultSet.class,ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);
            query.registerStoredProcedureParameter("OUT_CANT_REGISTRO",Integer.class,ParameterMode.OUT);


            query.setParameter("IN_PAR_BUSQUEDA",insb.getImpBusqueda());
            query.setParameter("IN_CANT_REG", insb.getCantReg());
            query.setParameter("IN_NUM_PAGINA",insb.getNumPag());


            Integer cant = (Integer) query.getOutputParameterValue("OUT_CANT_REGISTRO");
            String  msj = (String) query.getOutputParameterValue("OUT_MENSAJE");


            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;
                BigDecimal idUsuario = (BigDecimal) tuple[0];
                BigDecimal idPersona = (BigDecimal) tuple[1];
                String nroDocumento = (String) tuple[2];
                String nomApellidos = (String) tuple[3];
                String descOficina = (String) tuple[4];

                HashMap<String,Object> m = new HashMap<>();

                m.put("idUsuario",idUsuario);
                m.put("idPersona",idPersona);
                m.put("nroDocumento",nroDocumento);
                m.put("nomApellidos",nomApellidos);
                m.put("descOficina",descOficina);

                result.add(m);


            }

            outListaPaginada.setLista(result);
            outListaPaginada.setCantidad(cant);
            outListaPaginada.setMensaje(msj);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return outListaPaginada;
    }



    @Override
    public Integer creaResponsable(inResponsable is) {

        Integer result = 0;
         try{

             StoredProcedureQuery query= em.createStoredProcedureQuery("SP_INSERTAR_RESPONSABLE");

             query.registerStoredProcedureParameter("IN_ID_PERSONA", Long.class, ParameterMode.IN);
             query.registerStoredProcedureParameter("IN_ID_SISTEMA", Integer.class, ParameterMode.IN);

             query.registerStoredProcedureParameter("OUT_MENSAJE", Integer.class,ParameterMode.OUT);

             query.setParameter("IN_ID_PERSONA", is.getIdPersona());
             query.setParameter("IN_ID_SISTEMA", is.getIdSistema());
             query.execute();

             result = (Integer)query.getOutputParameterValue("OUT_MENSAJE");


         }catch (Exception e){

             e.printStackTrace();
         }finally {
             em.close();
         }


        return result;
    }

    @Override
    public String deshabilitarResponsable(Integer cod) {

        String result="";

        try{

            StoredProcedureQuery query=em.createStoredProcedureQuery("SP_DESHABILITAR_RESPONSABLE");
            query.registerStoredProcedureParameter("IN_CODIGO",Integer.class,ParameterMode.IN);

            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_CODIGO",cod);
            query.execute();
            result = (String) query.getOutputParameterValue("OUT_MENSAJE");

        }catch(Exception e){
            e.printStackTrace();

        }finally {
            em.close();
        }

        return result;
    }

    @Override
    public List<outSistemaSimple> listarSistemaResponsable(Integer cod) {

        List<outSistemaSimple> result = new ArrayList<>();

        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_LISTAR_SISTEMA_RESPONSABLE");
            query.registerStoredProcedureParameter("IN_CODIGO", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST",ResultSet.class,ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_CODIGO",cod);

            List<outSistemaSimple> ls = query.getResultList();

            for (Object item:ls){
                Object[] tuple = (Object[]) item;
                BigDecimal idSistema = (BigDecimal) tuple[0];
                String descSistema = (String) tuple[1];
                BigDecimal estado = (BigDecimal) tuple[2];

                outSistemaSimple ins = new outSistemaSimple();

                ins.setId(idSistema.longValue());
                ins.setDescripcion(descSistema);
                ins.setEstado(estado.longValue());

                result.add(ins);
            }


        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return result;
    }

    @Override
    public List<outUsuarioOficina> listaUsuarioOficina(inResponsable insb) {

        List<outUsuarioOficina> result = new ArrayList<>();

        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_LISTAR_USUARIO_OFICINA");

            query.registerStoredProcedureParameter("IN_ID_OFICINA", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_SISTEMA", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST",ResultSet.class,ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_ID_OFICINA",insb.getIdOficina());
            query.setParameter("IN_ID_SISTEMA", insb.getIdSistema());



            List<outUsuarioOficina> ls = query.getResultList();

            for (Object item:ls){
                Object[] tuple = (Object[]) item;
                BigDecimal id = (BigDecimal) tuple[0];
                String persona = (String) tuple[1];

                outUsuarioOficina ins = new outUsuarioOficina();

                ins.setId(id.longValue());
                ins.setPersona(persona);

                result.add(ins);
            }


        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return result;
    }

    @Override
    public outListaPaginada listaResponsableSistema(inBuscarResponsable insb) {

        outListaPaginada outListaPaginada = new outListaPaginada();
        List<HashMap<String,Object>> result = new ArrayList<>();


        try{

            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_LISTAR_RESPONSABLE_SISTEMA");

            query.registerStoredProcedureParameter("IN_ID_SISTEMA", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CANT_REG", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_NUM_PAGINA", Integer.class,ParameterMode.IN);

            query.registerStoredProcedureParameter("OUT_LISTA", ResultSet.class,ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);
            query.registerStoredProcedureParameter("OUT_CANT_REGISTRO",Integer.class,ParameterMode.OUT);

            query.setParameter("IN_ID_SISTEMA", insb.getIdSistema());
            query.setParameter("IN_CANT_REG", insb.getCantReg());
            query.setParameter("IN_NUM_PAGINA",insb.getNumPag());


            Integer cant = (Integer) query.getOutputParameterValue("OUT_CANT_REGISTRO");
            String  msj = (String) query.getOutputParameterValue("OUT_MENSAJE");


            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;
                BigDecimal idReponsable = (BigDecimal) tuple[0];
                BigDecimal idPersona = (BigDecimal) tuple[1];
                String nroDocumento = (String) tuple[2];
                String nomApellidos = (String) tuple[3];
                BigDecimal idSistema = (BigDecimal) tuple[4];
                String descSistema = (String) tuple[5];
                String descOficina = (String) tuple[6];
                BigDecimal estado = (BigDecimal) tuple[7];

                HashMap<String,Object> m = new HashMap<>();

                m.put("idReponsable",idReponsable);
                m.put("idPersona",idPersona);
                m.put("nroDocumento",nroDocumento);
                m.put("nomApellidos",nomApellidos);
                m.put("descOficina",descOficina);
                m.put("idSistema",idSistema);
                m.put("descSistema",descSistema);
                m.put("estado",estado);

                result.add(m);


            }

            outListaPaginada.setLista(result);
            outListaPaginada.setCantidad(cant);
            outListaPaginada.setMensaje(msj);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

        return outListaPaginada;
    }
}
