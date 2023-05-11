package pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.Security;
/**
 * @author Diego Zacarias Sanchez
 */
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
