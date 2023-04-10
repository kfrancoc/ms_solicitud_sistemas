package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
/**
 * @author Diego Zacarias Sanchez
 */
@MappedSuperclass
@JsonIgnoreProperties(
        value = {"creadoPor", "actualizadoPor"},
        allowGetters = true
)

@Data
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    @Column(name = "ID_USUARIO_REG",updatable = false)
    private Long creadoPor;

    @LastModifiedBy
    @Column(name = "ID_USUARIO_MOD")
    private Long actualizadoPor;

    @Column(name = "HABILITADO")
    private Integer habilitado;


}
