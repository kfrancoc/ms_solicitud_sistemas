package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;
/**
 * @author Diego Zacarias Sanchez
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"fechaRegistro", "fechaModificacion"},
        allowGetters = true
)
@Data
public class DateAudit implements Serializable {

    @CreatedDate
    @Column(name = "FECHA_REGISTRO")
    private Instant fechaRegistro;

    @LastModifiedDate
    @Column(name = "FECHA_MODIFICA")
    private Instant fechaModificacion;


}
