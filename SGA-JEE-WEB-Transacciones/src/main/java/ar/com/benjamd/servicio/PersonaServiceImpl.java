package ar.com.benjamd.servicio;

import ar.com.benjamd.datos.IPersonaDao;
import ar.com.benjamd.domain.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PersonaServiceImpl implements IPersonaServiceRemote, IPersonaService {

    @Inject
    private IPersonaDao personaDao;

    @Resource
    private SessionContext contexto;

    @Override
    public List<Persona> listarPersonas() {

        return personaDao.findAllPersonas();
    }

    @Override
    public Persona encontarPersonaPorId(Persona persona) {

        return personaDao.findAllPersonaById(persona);
    }

    @Override
    public Persona encontarPersonaPorEmail(Persona persona) {
        return personaDao.findAllPersonaByEmail(persona);
    }

    @Override
    public void registarPersona(Persona persona) {
        personaDao.insertPersona(persona);
    }

    @Override
    public void modificarPersona(Persona persona) {
        try {
            personaDao.updatePersona(persona);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);
        }
    }

    @Override
    public void eliminarPersona(Persona persona) {
        personaDao.deletePersona(persona);
    }

}
