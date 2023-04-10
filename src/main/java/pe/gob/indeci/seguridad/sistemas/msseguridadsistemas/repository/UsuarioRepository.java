package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsernameOrEmail(String username, String email);
    List<Usuario> findByIdIn(List<Long> userIds);
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Usuario findUserByEmail(String email);
    @Query("from Usuario o where o.resetPasswordToken =:token and o.habilitado=:habilitado")
    Usuario findByResetPasswordToken(String token,Integer habilitado);

    @Query("from Usuario o where o.habilitado=1")
    List<Usuario> findAllByHabilitado();
    @Modifying
    @Query("update Usuario o set o.email =:email  where o.id=:id")
    void updateUsuario(String email, Long id);
    @Modifying
    @Query("update Usuario o set o.habilitado = 0 where o.id=:id")
    void deleteById(Long id);




}
