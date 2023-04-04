package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.maestras;


import javax.persistence.*;
import java.util.Date;

@Table(name="INSTITUCION")
public class Institucion {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_INSTITUCION")
@SequenceGenerator(name = "SQ_INSTITUCION", sequenceName = "SQ_ID_INSTITUCION", allocationSize = 1)

    @Column (name="ID_INSTITUCION")
    private Long idInstitucion;
    @Column (name = "ABREVIATURA_INSTITUCION")
    private String abreviaturaInstitucion;
    @Column (name = "DESCRIPCION_INSTITUCION")
    private String descripcionInstitucion;
    @Column (name="ESTADO")
    private Long estadoInstitucion;
    @Column (name="FEC_CREACION")
    private Date fechaCreacion;
    @Column (name="ID_USUARIO_CREADO")
    private Long idUsuarioCreado;
    @Column (name="FEC_MODIFICACION")
    private Date fechaModificacion;
    @Column (name="ID_USUARIO_MODIFICADO")
    private Long idUsuarioModificado;
    @Column (name="TIPO_INSTITUCION")
    private String tipoInstitucion;





}
