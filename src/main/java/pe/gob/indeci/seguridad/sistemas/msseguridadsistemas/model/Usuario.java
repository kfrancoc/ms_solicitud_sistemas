package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "sistemas")
@Table(name = "USUARIO")
public class Usuario extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO_ID", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    Long id;

    @Column(name="username",length = 320)
    String username;

    @Column(name="email",length = 320)
    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email cannot be empty")
    String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should have min 6 characters")
    @JsonIgnore
    @Column(name="password")
    String password;

    @Column(name = "reset_password_token")
    @Size(max = 30)
    private String resetPasswordToken;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_roles",
            joinColumns = @JoinColumn(name = "id_usuario",foreignKey = @ForeignKey(name = "usuario_roles")),
            inverseJoinColumns = @JoinColumn(name = "id_rol" ,foreignKey = @ForeignKey(name = "rol_usuarios")))
    private Set<Rol> roles = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "usuario")
    Set<SistemaUsuario> sistemaUsuarios;

    public Usuario() {
    }

    public Usuario(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }

}
