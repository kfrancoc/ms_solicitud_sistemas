package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.Security;
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
