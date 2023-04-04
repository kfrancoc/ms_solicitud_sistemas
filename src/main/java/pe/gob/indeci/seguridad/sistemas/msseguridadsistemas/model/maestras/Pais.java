package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.maestras;

import javax.persistence.*;
import java.util.Date;

@Table(name="PAIS")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_PAIS")
    @SequenceGenerator(name = "SQ_PAIS", sequenceName = "SQ_ID_PAIS", allocationSize = 1)
    @Column(name="ID_PAIS")
    private Long idPais;
    @Column (name = "ABREVIATURA_PAIS")
    private String abreviaturaPais;
    @Column (name = "DESCRIPCION_PAIS")
    private String descripcionPais;
    @Column (name = "PREFIJO_PAIS")
    private String prefijoPais;
    @Column (name="FEC_CREACION")
    private Date fechaCreacion;
    @Column(name="ID_USUARIO_CREADO")
    private Long idUsuarioCreado;
    @Column (name="FEC_MODIFICACION")
    private Date fechaModificacion;
    @Column(name="ID_USUARIO_MODIFICADO")
    private Long idUsuarioModificado;
    @Column (name="ESTADO_PAIS")
    private Long estadoPais;

}
