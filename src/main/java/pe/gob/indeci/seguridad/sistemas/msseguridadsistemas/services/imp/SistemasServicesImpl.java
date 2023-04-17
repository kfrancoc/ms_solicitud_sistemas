package pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inBusquedaSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSistemaAcces;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.input.inSistemas;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outBusquedaSistema;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outObtModPadre;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.model.output.outSistemaSimple;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.repository.SistemaRepository;
import pe.gob.indeci.seguridad.sistemas.msseguridadsistemas.services.SistemasServices;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class SistemasServicesImpl implements SistemasServices {

    @Autowired

    private SistemaRepository sistemaRepository;

    @Override

    public String crearSistema(inSistemas is){

        return this.sistemaRepository.crearSistema(is);

    }

    @Override
    public  List<inSistemas> detalleSistema(Integer cod){

        return  this.sistemaRepository.detalleSistema(cod);

    }

    @Override
    public  List<inSistemas>updateSistema(inSistemas is){

        return this.sistemaRepository.updateSistema(is);
    }

    @Override
    public List<inSistemas>eliminarSistema(Integer cod){

        return  this.sistemaRepository.eliminarSistema(cod);
    }

    @Override
    public outBusquedaSistema listarSistema(inBusquedaSistema inbs){
        return  this.sistemaRepository.listarSistema(inbs);
    }


    @Override
    public List<outSistemaSimple> listarSistemaSimple(){
        return this.sistemaRepository.listarSistemaSimple();

    }

    @Override
    public String insertSistemaAcceso(inSistemaAcces insis){
        return this.sistemaRepository.insertSistemaAcceso(insis);
    }

    @Override
    public outObtModPadre sistemaEnlace(){
        return this.sistemaRepository.sistemaEnlace();
    }
}
