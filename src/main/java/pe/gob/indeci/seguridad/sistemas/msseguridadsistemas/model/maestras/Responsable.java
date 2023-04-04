package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.maestras;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "SEGURIDAD", name = "RESPONSABLE_APROBACION")
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_RESPONSABLE_APROBACION")
    @SequenceGenerator(name = "SQ_RESPONSABLE_APROBACION", sequenceName = "SQ_ID_RESPONSABLE_APROBACION", allocationSize = 1)
    @Column(name ="ID_RESPONSABLE_APROBACION")
    private Long idResponsableAprobacion;

    @Column(name ="ID_PERSONA")
    private Long idPersona;
    @Column(name ="ID_SISTEMA")
    private Long idSistema;
    @Column(name ="ESTADO_RESPONSABLE")
    private Long EstadoResponsable;
    @Column(name ="FEC_CREACION")
    private Date fechaCreacion;
    @Column(name ="ID_USUARIO_CREADO")
    private Long idUsuarioCreado;
    @Column(name ="FEC_MODIFICACION")
    private Date fechaMOdificacion;
    @Column(name ="ID_USUARIO_MODIFICADO")
    private Long idUsuarioModificado;




}
