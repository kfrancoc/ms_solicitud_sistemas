package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.maestras;

import javax.persistence.*;
import java.util.Date;

@Table(name = "OFICINA")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_OFICINA")
    @SequenceGenerator(name = "SQ_OFICINA", sequenceName = "SQ_ID_OFICINA", allocationSize = 1)

    @Column(name="ID_OFICINA")
    private Long idOficina;
    @Column (name = "ABREVIATURA_OFICINA")
    private String abreviaturaOficina;
    @Column (name = "DESCRIPCION_OFICINA")
    private String descripcionOficina;
    @Column(name="ESTADO")
    private Long estado;
    @Column(name="FEC_CREACION")
    private Date fecCreacion;
    @Column(name="ID_USUARIO_CREADO")
    private Long idUsuarioCreado;
    @Column(name="FEC_MODIFICACION")
    private Date fecModificacion;
    @Column(name="ID_USUARIO_MODIFICADO")
    private Long idUsuarioModificado;
    @Column(name="ID_INSTITUCION")
    private Long idInstitucion;


}
