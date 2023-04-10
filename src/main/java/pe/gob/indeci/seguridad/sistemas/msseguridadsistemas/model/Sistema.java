package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "usuarios")
@Entity
@Table( name = "SISTEMA")
public class Sistema extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SISTEMA")
    @SequenceGenerator(name = "SQ_SISTEMA", sequenceName = "SQ_SISTEMA_ID", allocationSize = 1)
    @Column(name = "ID_SISTEMA")
    Long id;

    @Column(name="siglas",length = 20)
    @NotEmpty(message = "siglas cannot be empty")
    String siglas;

    @Column(name="descripcion",length = 4000)
    @NotEmpty(message = "descripcion cannot be empty")
    String descripcion;

    @Column(name="FECHA_PUBLICACION")
    String fechaPublicacion;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "sistema")
    Set<SistemaUsuario> sistemaUsuarios;


}
