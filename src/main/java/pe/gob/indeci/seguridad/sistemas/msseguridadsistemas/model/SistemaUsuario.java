package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class SistemaUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SISTEMA_USUARIO")
    @SequenceGenerator(name = "SQ_SISTEMA_USUARIO", sequenceName = "SQ_SISTEMA_USUARIO", allocationSize = 1)
    @Column(name = "ID_SISTEMA_USUARIO")
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_sistema", referencedColumnName = "id_sistema", insertable = false, updatable = false)
    Sistema sistema;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    Usuario usuario;


    Integer habilitado;

}
