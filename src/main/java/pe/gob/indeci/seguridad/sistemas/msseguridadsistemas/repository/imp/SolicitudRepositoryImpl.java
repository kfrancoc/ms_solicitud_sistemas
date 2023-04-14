package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.imp;

import org.springframework.stereotype.Repository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inArchivo;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaPersona;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inPersona;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSolicitud;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.*;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.SolicitudRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class SolicitudRepositoryImpl implements SolicitudRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public outBusquedaPersona busquedaPersona(inBusquedaPersona inbusp){

        outBusquedaPersona obp = new outBusquedaPersona();
        List<HashMap<String,Object>> result = new ArrayList<>();

        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_buscar_persona");
            query.registerStoredProcedureParameter("in_nro_documento", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("out_list_persona", Result.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("out_mensaje", String.class, ParameterMode.OUT);

            query.setParameter("in_nro_documento",inbusp.getNumeroDocumento());
            String mensaje = (String) query.getOutputParameterValue("out_mensaje");

            List<Object>ls = query.getResultList();

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

    @Override
    public Integer crearPersona(inPersona is) {
        Integer result = 0;

        try{
            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_INSERTAR_PERSONA");
            query.registerStoredProcedureParameter("IN_ID_TIPO_DOCUMENTO",Long.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_NUMERO_DOCUMENTO",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_APELLIDOS",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_NOMBRE",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_TIPO_SEXO",Character.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CORREO_INSITUCIONAL",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CORREO_PERSONAL",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_TELEFONO",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_PAIS",Long.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CODIGO_DISTRITO",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_INSTITUCION",Long.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_OFICINA",Long.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_CONDICION_LABORAL",Long.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_INTERNO_EXTERNO",Long.class,ParameterMode.IN);

            query.registerStoredProcedureParameter("OUT_MENSAJE",Integer.class,ParameterMode.OUT);


            query.setParameter("IN_ID_TIPO_DOCUMENTO",is.getIdtipodocumento());
            query.setParameter("IN_NUMERO_DOCUMENTO",is.getNumerodocumento());
            query.setParameter("IN_APELLIDOS",is.getApellidos());
            query.setParameter("IN_NOMBRE",is.getNombre());
            query.setParameter("IN_ID_TIPO_SEXO",is.getIdtiposexo());
            query.setParameter("IN_CORREO_INSITUCIONAL",is.getCorreoinsitucional());
            query.setParameter("IN_CORREO_PERSONAL",is.getCorreopersonal());
            query.setParameter("IN_TELEFONO",is.getTelefono());
            query.setParameter("IN_ID_PAIS",is.getIdpais());
            query.setParameter("IN_CODIGO_DISTRITO",is.getCodigodistrito());
            query.setParameter("IN_ID_INSTITUCION",is.getIdinstitucion());
            query.setParameter("IN_ID_OFICINA",is.getIdoficina());
            query.setParameter("IN_ID_CONDICION_LABORAL",is.getIdcondicionlaboral());
            query.setParameter("IN_INTERNO_EXTERNO",is.getInternoexterno());
            query.execute();

            result = (Integer) query.getOutputParameterValue("OUT_MENSAJE");

        }catch (Exception e){

            e.printStackTrace();

        }finally {
            em.close();
        }

        return result;
    }

    @Override
    public Integer crearSolicitud(inSolicitud is) {

        Integer result = 0;

      /*  if(is.getRuta() == null){
            is.setRuta("");
        }
        if(is.getNombre() == null){
            is.setNombre("");
        }
        if(is.getArchivo() == null){
            is.setArchivo("");
        }
        if(is.getTamanio() == null){
            is.setTamanio("");
        }
        if(is.getTipo() == null){
            is.setTipo("");
        }
        if(is.getDescripcion() == null){
            is.setDescripcion("");
        }*/

        try{

            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_INSERTAR_SOLICITUD");
            query.registerStoredProcedureParameter("IN_DNI_PERSONA_CREA", String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_DNI_PERSONA_SOLICITADO", String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CADENA_ID", String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CADENA_DESC",String.class,ParameterMode.IN);

            /*query.registerStoredProcedureParameter("IN_RUTA_DOC",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_NOMBRE_DOC",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ARCHIVO_DOC",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_TAMANIO_DOC",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_TIPO_DOC",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_DESCRIPCION_DOC",String.class,ParameterMode.IN);*/

            query.registerStoredProcedureParameter("OUT_COD", Integer.class,ParameterMode.OUT);
            query.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            query.setParameter("IN_DNI_PERSONA_CREA", is.getInDniPersonaCrea());
            query.setParameter("IN_DNI_PERSONA_SOLICITADO", is.getInDniPersonaSolicitado());
            query.setParameter("IN_CADENA_ID", is.getIdCadena());
            query.setParameter("IN_CADENA_DESC", is.getDesCadena());

            /*query.setParameter("IN_RUTA_DOC", is.getRuta());
            query.setParameter("IN_NOMBRE_DOC", is.getNombre());
            query.setParameter("IN_ARCHIVO_DOC", is.getArchivo());
            query.setParameter("IN_TAMANIO_DOC", is.getTamanio());
            query.setParameter("IN_TIPO_DOC", is.getTipo());
            query.setParameter("IN_DESCRIPCION_DOC", is.getDescripcion());*/

            query.execute();

            result = (Integer) query.getOutputParameterValue("OUT_COD");



        }catch (Exception e){
            e.printStackTrace();

        }finally {
            em.close();
        }
        return result;
    }


    @Override
    public List<outPerfil> listarPerfil(Integer cod) {

        List<outPerfil> result =  new ArrayList<>();

        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_LISTAR_PERFIL_SIMPLE");

            query.registerStoredProcedureParameter("IN_ID_SISTEMA",Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_ID_SISTEMA",cod);

            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;
                BigDecimal idPerfil = (BigDecimal) tuple[0];
                String abrevPerfil = (String) tuple[1];
                String descPerfil = (String) tuple[2];
                BigDecimal idSistema = (BigDecimal) tuple[3];

                outPerfil os= new outPerfil();

                os.setIdPerfil(idPerfil.longValue());
                os.setAbreviaturaPerfil(abrevPerfil);
                os.setDescripcionPerfil(descPerfil);
                os.setIdSistema(idSistema.longValue());


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
    public List<outListaSimple> listarPaisSimple(){

        List<outListaSimple> result = new ArrayList<>();

        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_LISTAR_PAIS_SIMPLE");
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            List<outListaSimple> ls = query.getResultList();

            for (Object item : ls){

                Object[] tuple = (Object[]) item;
                BigDecimal idSistema = (BigDecimal) tuple[0];
                String descripcion  = (String) tuple[1];
                String abreviatura  = (String) tuple[2];


                outListaSimple os = new outListaSimple();

                os.setId(idSistema.longValue());
                os.setDescripcion(descripcion);
                os.setAbreviatura(abreviatura);


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
    public List<outListaSimple> listarCondicionSimple() {
        List<outListaSimple> result = new ArrayList<>();

        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_LISTAR_COND_LABORAL_SIMPLE");

            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            List<outListaSimple> ls = query.getResultList();

            for (Object item : ls){

                Object[] tuple = (Object[]) item;
                BigDecimal idSistema = (BigDecimal) tuple[0];
                String descripcion  = (String) tuple[1];


                outListaSimple os = new outListaSimple();

                os.setId(idSistema.longValue());
                os.setDescripcion(descripcion);


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
    public List<outListaSimple> listarInstitucionTipo(String cod) {

        List<outListaSimple> result =  new ArrayList<>();

        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_LISTAR_INSTITUCION_TIPO");

            query.registerStoredProcedureParameter("IN_ID_TIPO",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_ID_TIPO",cod);

            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;
                BigDecimal id = (BigDecimal) tuple[0];
                String descripcion = (String) tuple[1];


                outListaSimple os= new outListaSimple();

                os.setId(id.longValue());
                os.setDescripcion(descripcion);



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
    public List<outListaSimple> listarSistemaSimple() {
        List<outListaSimple> result = new ArrayList<>();

        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_LISTAR_SISTEMA_SIMPLE");
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            List<outListaSimple> ls = query.getResultList();

            for (Object item : ls){

                Object[] tuple = (Object[]) item;
                BigDecimal idSistema = (BigDecimal) tuple[0];
                String descripcion  = (String) tuple[1];


                outListaSimple os = new outListaSimple();

                os.setId(idSistema.longValue());
                os.setDescripcion(descripcion);


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
    public List<outListaSimple> listarOficinaSimple(Integer cod) {
        List<outListaSimple> result = new ArrayList<>();


        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_LISTAR_OFICINA");
            query.registerStoredProcedureParameter("IN_FILTRO",Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_FILTRO",cod);

            List<outListaSimple> ls = query.getResultList();

            for (Object item : ls){

                Object[] tuple = (Object[]) item;
                BigDecimal idSistema = (BigDecimal) tuple[0];
                String descripcion  = (String) tuple[1];


                outListaSimple os = new outListaSimple();

                os.setId(idSistema.longValue());
                os.setDescripcion(descripcion);


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
    public List<outListaSimple> listarUbigeo(String cod) {
        List<outListaSimple> result =  new ArrayList<>();



        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_LISTAR_UBIGEO");

            query.registerStoredProcedureParameter("IN_UBIGEO",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_UBIGEO",cod);

            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;
                String id = (String) tuple[0];
                String descripcion = (String) tuple[1];


                outListaSimple os= new outListaSimple();

                os.setCodigo(id);
                os.setDescripcion(descripcion);



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
    public Integer estadoSolicitud(inSolicitud is) {

        Integer result = 0;

        try{

            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_ESTADO_SOLICITUD");
            query.registerStoredProcedureParameter("IN_ID_SOLICITUD", Long.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_SOL_DET", Long.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_SISTEMA", Long.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_DESC_APROB",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_OBS",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ESTADO",Integer.class,ParameterMode.IN);

            query.registerStoredProcedureParameter("OUT_COD", Integer.class,ParameterMode.OUT);
            query.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);

            query.setParameter("IN_ID_SOLICITUD", is.getIdSolicitud());
            query.setParameter("IN_ID_SOL_DET", is.getIdSolicitudDetalle());
            query.setParameter("IN_ID_SISTEMA", is.getIdSistema());
            query.setParameter("IN_DESC_APROB", is.getDescAprobacion());
            query.setParameter("IN_OBS", is.getObservacion());
            query.setParameter("IN_ESTADO", is.getEstado());
            query.execute();

            result = (Integer) query.getOutputParameterValue("OUT_COD");



        }catch (Exception e){
            e.printStackTrace();

        }finally {
            em.close();
        }
        return result;
    }

    @Override
    public List<outDetalleSolicitud> detalleSolicitud(inSolicitud is) {

        List<outDetalleSolicitud> result =  new ArrayList<>();



        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_DETALLE_SOLICITUD");
            query.registerStoredProcedureParameter("IN_ID_SOLICITUD", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_DET_SOL", Long.class, ParameterMode.IN);

            query.registerStoredProcedureParameter("OUT_LISTA", Result.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE", String.class, ParameterMode.OUT);

            query.setParameter("IN_ID_SOLICITUD",is.getIdSolicitud());
            query.setParameter("IN_ID_DET_SOL",is.getIdSolicitudDetalle());


            List<outDetalleSolicitud> ls = query.getResultList();


            for (Object item : ls){
                Object[]tuple = (Object[]) item;
                BigDecimal idSolicitud = (BigDecimal) tuple[0];
                BigDecimal idSolDetalle = (BigDecimal) tuple[1];
                String descSistema = (String) tuple[2];
                String descSolicitud = (String) tuple[3];
                String descAprobacion = (String) tuple[4];
                String ObsSolicitud = (String) tuple[5];
                String dniPersSolicito = (String) tuple[6];


                outDetalleSolicitud ins = new outDetalleSolicitud();

                ins.setIdSolicitud(idSolicitud.longValue());
                ins.setIdSolDetalle(idSolDetalle.longValue());
                ins.setDescSistema(descSistema);
                ins.setDescSolicitud(descSolicitud);
                ins.setDescAprobacion(descAprobacion);
                ins.setObsSolicitud(ObsSolicitud);
                ins.setDniPersSolicito(dniPersSolicito);



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
    public List<outListaSimple> listarCiudad(Integer cod) {
        List<outListaSimple> result =  new ArrayList<>();



        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_LISTAR_CIUDAD");

            query.registerStoredProcedureParameter("IN_ID_PAIS",Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_ID_PAIS",cod);

            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;
                BigDecimal id = (BigDecimal) tuple[0];
                String descripcion = (String) tuple[1];
                BigDecimal idPais = (BigDecimal) tuple[2];

                outListaSimple os= new outListaSimple();

                os.setId(id.longValue());
                os.setDescripcion(descripcion);
                os.setIdPais(idPais.longValue());



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
    public outListaPaginada listarCreaSolicitud(inSolicitud insb) {
        outListaPaginada outListaPaginada = new outListaPaginada();
        List<HashMap<String,Object>> result = new ArrayList<>();

        if(insb.getImpBusqueda() == null){
            insb.setImpBusqueda("");
        }
        if(insb.getEstado() == null){
            insb.setEstado(0);
        }

        try{

            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_LISTAR_CREA_SOLICITUD");


            query.registerStoredProcedureParameter("IN_PAR_BUSQUEDA",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CANT_REG", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_NUM_PAGINA", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_DNI_CREA", String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ESTADO", Integer.class,ParameterMode.IN);


            query.registerStoredProcedureParameter("OUT_LISTA", ResultSet.class,ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);
            query.registerStoredProcedureParameter("OUT_CANT_REGISTRO",Integer.class,ParameterMode.OUT);


            query.setParameter("IN_PAR_BUSQUEDA",insb.getImpBusqueda());
            query.setParameter("IN_CANT_REG", insb.getCantReg());
            query.setParameter("IN_NUM_PAGINA",insb.getNumPag());
            query.setParameter("IN_DNI_CREA", insb.getInDniPersonaCrea());
            query.setParameter("IN_ESTADO",insb.getEstado());


            Integer cant = (Integer) query.getOutputParameterValue("OUT_CANT_REGISTRO");
            String  msj = (String) query.getOutputParameterValue("OUT_MENSAJE");


            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;

                BigDecimal idSolicitud = (BigDecimal) tuple[0];
                String dniCrea = (String) tuple[1];
                String dniSolicita = (String) tuple[2];
                String persona = (String) tuple[3];
                Date fecAper = (Date) tuple[4];
                Date fecCierre = (Date) tuple[5];
                BigDecimal estadoSol = (BigDecimal) tuple[6];

                HashMap<String,Object> m = new HashMap<>();


                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");


                if (fecAper != null){
                    String fecha_aper = format1.format(fecAper);
                    m.put("fecAper",fecha_aper);
                }

                if (fecCierre != null){
                    String fecha_cierre = format1.format(fecCierre);
                    m.put("fecCierre",fecha_cierre);
                }

                m.put("idSolicitud",idSolicitud);

                m.put("dniCrea",dniCrea);
                m.put("persona",persona);
                m.put("dniSolicita",dniSolicita);
                m.put("estadoSol",estadoSol);


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
    public List<outDetalleSolicitud> detalleCreaSolicitud(Integer cod) {
        List<outDetalleSolicitud> result =  new ArrayList<>();



        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_DETALLE_CREA_SOLICITUD");

            query.registerStoredProcedureParameter("IN_CODIGO",Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_CODIGO",cod);

            List<Object> ls = query.getResultList();

            for (Object item : ls){


                Object[] tuple = (Object[]) item;
                BigDecimal idDetalle = (BigDecimal) tuple[0];
                BigDecimal idSolicitud = (BigDecimal) tuple[1];
                String descripcion = (String) tuple[2];
                String descSolicitud = (String) tuple[3];
                String descAprobacion = (String) tuple[4];
                Date fechApertura =(Date) tuple [5];
                Date fechAprobacion =(Date) tuple [6];
                Date fechCreaUsuario =(Date) tuple [7];
                String observacion = (String) tuple[8];
                Character estadoDetalle = (Character) tuple[9];


                outDetalleSolicitud os= new outDetalleSolicitud();
                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

                os.setIdSolDetalle(idDetalle.longValue());
                os.setIdSolicitud(idSolicitud.longValue());
                os.setDescSistema(descripcion);
                os.setDescSolicitud(descSolicitud);
                os.setDescAprobacion(descAprobacion);

                if (fechApertura != null){
                    String fech_Apertura = format1.format(fechApertura);
                    os.setFechApertura(fech_Apertura);
                }
                if (fechAprobacion != null){
                    String fech_Aprobacion = format1.format(fechAprobacion);
                    os.setFechAprobacion(fech_Aprobacion);
                }
                if (fechCreaUsuario != null){
                    String fech_CreaUsuario = format1.format(fechCreaUsuario);
                    os.setFechCreaUsuario(fech_CreaUsuario);
                }

                os.setObsSolicitud(observacion);
                os.setEstadoDetalle(estadoDetalle);


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
    public outListaPaginada listarAprobSolicitud(inSolicitud insb) {
        outListaPaginada outListaPaginada = new outListaPaginada();
        List<HashMap<String,Object>> result = new ArrayList<>();

        if(insb.getImpBusqueda() == null){
            insb.setImpBusqueda("");
        }
        if(insb.getEstadodet() == null){
            insb.setEstadodet("");
        }
        if(insb.getIdSistema() == null){
            insb.setIdSistema(0);
        }



        try{

            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_LISTAR_APROB_SOLICITUD");


            query.registerStoredProcedureParameter("IN_PAR_BUSQUEDA",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_CANT_REG", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_NUM_PAGINA", Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_DNI_APRO", String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ESTADO", String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("IN_ID_SISTEMA", Integer.class,ParameterMode.IN);


            query.registerStoredProcedureParameter("OUT_LISTA", ResultSet.class,ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE", String.class,ParameterMode.OUT);
            query.registerStoredProcedureParameter("OUT_CANT_REGISTRO",Integer.class,ParameterMode.OUT);


            query.setParameter("IN_PAR_BUSQUEDA",insb.getImpBusqueda());
            query.setParameter("IN_CANT_REG", insb.getCantReg());
            query.setParameter("IN_NUM_PAGINA",insb.getNumPag());
            query.setParameter("IN_DNI_APRO", insb.getInDniPersonaAprobacion());
            query.setParameter("IN_ESTADO",insb.getEstadodet());
            query.setParameter("IN_ID_SISTEMA",insb.getIdSistema());



            Integer cant = (Integer) query.getOutputParameterValue("OUT_CANT_REGISTRO");
            String  msj = (String) query.getOutputParameterValue("OUT_MENSAJE");


            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;

                BigDecimal idSolicitud = (BigDecimal) tuple[0];
                BigDecimal idDetalle = (BigDecimal) tuple[1];
                String dniSolicita = (String) tuple[2];
                String persona = (String) tuple[3];
                String descSistema = (String) tuple[4];
                String descSolicitud = (String) tuple[5];
                String descArpobacion = (String) tuple[6];
                Date fecAper = (Date) tuple[7];
                Date fecApro = (Date) tuple[8];
                Date fecUsuario = (Date) tuple[9];
                String descObservacion = (String) tuple[10];
                Character estadoDetalle = (Character) tuple[11];

                HashMap<String,Object> m = new HashMap<>();

                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

                m.put("idSolicitud",idSolicitud);
                m.put("idDetalle",idDetalle);
                m.put("dniSolicita",dniSolicita);
                m.put("persona",persona);
                m.put("descSistema",descSistema);
                m.put("descSolicitud",descSolicitud);
                m.put("descArpobacion",descArpobacion);

                if (fecAper != null){
                    String fecha_aper = format1.format(fecAper);
                    m.put("fecAper",fecha_aper);
                }

                if (fecApro != null){
                    String fec_Apro = format1.format(fecApro);
                    m.put("fecApro",fec_Apro);
                }
                if (fecUsuario != null){
                    String fec_Usuario = format1.format(fecUsuario);
                    m.put("fecUsuario",fec_Usuario);
                }

                m.put("descObservacion",descObservacion);
                m.put("estadoDetalle",estadoDetalle);


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
    public List<outDetalleSolicitud> detalleAprobSolicitud(Integer cod) {

        List<outDetalleSolicitud> result =  new ArrayList<>();


        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_DETALLE_APROB_SOLICITUD");

            query.registerStoredProcedureParameter("IN_ID_DETALLE",Integer.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_ID_DETALLE",cod);

            List<Object> ls = query.getResultList();

            for (Object item : ls){


                Object[] tuple = (Object[]) item;
                BigDecimal idDetalle = (BigDecimal) tuple[0];
                BigDecimal idSolicitud = (BigDecimal) tuple[1];
                String descripcion = (String) tuple[2];
                String descSolicitud = (String) tuple[3];
                String descAprobacion = (String) tuple[4];
                Date fechApertura =(Date) tuple [5];
                Date fechAprobacion =(Date) tuple [6];
                Date fechCreaUsuario =(Date) tuple [7];
                String observacion = (String) tuple[8];
                Character estadoDetalle = (Character) tuple[9];


                outDetalleSolicitud os= new outDetalleSolicitud();
                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

                os.setIdSolDetalle(idDetalle.longValue());
                os.setIdSolicitud(idSolicitud.longValue());
                os.setDescSistema(descripcion);
                os.setDescSolicitud(descSolicitud);
                os.setDescAprobacion(descAprobacion);

                if (fechApertura != null){
                    String fech_Apertura = format1.format(fechApertura);
                    os.setFechApertura(fech_Apertura);
                }
                if (fechAprobacion != null){
                    String fech_Aprobacion = format1.format(fechAprobacion);
                    os.setFechAprobacion(fech_Aprobacion);
                }
                if (fechCreaUsuario != null){
                    String fech_CreaUsuario = format1.format(fechCreaUsuario);
                    os.setFechCreaUsuario(fech_CreaUsuario);
                }

                os.setObsSolicitud(observacion);
                os.setEstadoDetalle(estadoDetalle);


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
    public List<outListaSimple> listarSistemaAprob(String cod) {
        List<outListaSimple> result =  new ArrayList<>();



        try {
            StoredProcedureQuery query =  em.createStoredProcedureQuery("SP_LISTAR_SIS_APROB");

            query.registerStoredProcedureParameter("IN_DOC_APROB",String.class,ParameterMode.IN);
            query.registerStoredProcedureParameter("OUT_LIST", ResultSet.class, ParameterMode.REF_CURSOR);
            query.registerStoredProcedureParameter("OUT_MENSAJE",String.class,ParameterMode.OUT);

            query.setParameter("IN_DOC_APROB",cod);

            List<Object> ls = query.getResultList();

            for (Object item : ls){
                Object[] tuple = (Object[]) item;
                BigDecimal id = (BigDecimal) tuple[0];
                String descripcion = (String) tuple[1];


                outListaSimple os= new outListaSimple();

                os.setId(id.longValue());
                os.setDescripcion(descripcion);




                result.add(os);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return result;
    }





}



