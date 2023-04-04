package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.maestras;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "SEGURIDAD", name = "CONDICION_LABORAL")
public class CondicionLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CONDICION_LABORAL")
    @SequenceGenerator(name = "SQ_CONDICION_LABORAL", sequenceName = "SQ_CONDICION_LABORAL_ID", allocationSize = 1)
    @Column(name = "ID_CONDICION_LABORAL")
    private Long idCondicionLaboral;

    @Column(name = "DESCRIPCION_CONDICION_LABORAL")
    private  String descCondicionLaboral;

    @Column(name = "ESTADO_CONDICION_LABORAL")
    private  Long estadoCondicionLaboral;
    @Column(name = "ID_USUARIO_CREADO")
    private Long idUsuarioCreado;

    @Column(name = "FEC_CREACION")
    private  Date fecCreacion;

    @Column(name = "ID_USUARIO_MODIFICADO")
    private Long idUsuarioModificado;
    @Column(name = "FEC_MODIFICACION")
    private  Date fecModificado;

}


