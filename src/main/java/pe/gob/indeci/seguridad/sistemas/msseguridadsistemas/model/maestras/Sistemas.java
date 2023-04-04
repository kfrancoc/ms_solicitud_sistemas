package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.maestras;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SISTEMA")

public class Sistemas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_SISTEMA")
    @SequenceGenerator(name = "SQ_SISTEMA", sequenceName = "SQ_SISTEMA_ID", allocationSize = 1)
    @Column(name = "ID_SISTEMAS")
    private Long id;

    @Column(name = "DESCRIPCION")
    private String descSistemas;

    @Column(name = "SIGLAS")
    private String siglas;

    @Column(name = "ESTADO_SISTEMA")
    private  Long estadoSistema;

    @Column(name = "URL")
    private  String url;

    @Column(name = "FEC_CREACION")
    private Date fecCreacion;

    @Column(name = "ID_USUARIO_CREADO")
    private  Long idUsuarioCreado;

    @Column(name = "FEC_MODIFICACION")
    private  Date fecModificacion;

    @Column(name = "ID_USUARIO_MODIFICADO")
    private Long idUsuMod;

    @Column(name = "FEC_PUBLICACION")
    private Date fecPublicacion;

}
