package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.maestras;

import javax.persistence.*;
import java.util.Date;

@Table (name="TIPO_DOCUMENTO")
public class TipoDocumento {

    /// pendiente creacion de secuencia
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_TIPDOC")
    @SequenceGenerator(name = "SQ_TIPDOC", sequenceName = "SQ_TIPDOC_ID", allocationSize = 1)

    @Column (name="ID_TIPO_DOCUMENTO")
    private Long idDocumento;
    @Column (name = "DESCRIPCION_TIPO_DOC")
    private String descripcionDocumento;
    @Column (name = "ESTADO")
    private Long estado;
    @Column (name = "FEC_CREACION")
    private Date fechaCreacion;
    @Column (name = "ID_USUARIO_CREADO")
    private Long idUsuarioCreado;
    @Column (name = "FEC_MODIFICACION")
    private Date fechaModificacion;
    @Column (name = "ID_USUARIO_MODIFICADO")
    private Long idUsuarioModificado;


}
