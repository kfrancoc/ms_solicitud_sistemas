package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
/**
 * @author Diego Zacarias Sanchez
 */
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROLES")
    @SequenceGenerator(name = "SQ_ROLES", sequenceName = "SQ_ROLES_ID", allocationSize = 1 )
    @Column(name="role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Rol() {
        super();
    }

    public Rol(RoleName name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }


}
