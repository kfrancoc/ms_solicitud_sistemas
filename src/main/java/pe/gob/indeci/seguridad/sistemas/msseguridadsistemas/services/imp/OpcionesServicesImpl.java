package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.imp;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inNivelModulo2;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inNivelSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inPerfilOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outOpcion;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.OpcionesRepository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.OpcionesServices;

@Service
@Transactional
public class OpcionesServicesImpl implements OpcionesServices {
    @Autowired
    private OpcionesRepository opcionesRepository;

    public OpcionesServicesImpl() {
    }

    public List<inNivelSistema> listarNivelSistema(Integer id_sistema) {
        return this.opcionesRepository.listarNivelSistema(id_sistema);
    }

    public List<inNivelModulo2> listarNivelModulo(Integer id_sistema, Integer id_nivel) {
        return this.opcionesRepository.listarNivelModulo(id_sistema, id_nivel);
    }

    public List<outOpcion> crearOpcion(outOpcion om) {
        return this.opcionesRepository.crearOpcion(om);
    }

    public outBusquedaOpcion listarOpcion(inBusquedaOpcion inbo) {
        return this.opcionesRepository.listarOpcion(inbo);
    }

    public List<inPerfilOpcion> listarPerfilOpcion(Integer id_perfil, Integer id_modulo) {
        return this.opcionesRepository.listarPerfilOpcion(id_perfil, id_modulo);
    }
}
