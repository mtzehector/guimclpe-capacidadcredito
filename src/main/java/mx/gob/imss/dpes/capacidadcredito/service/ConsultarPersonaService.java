/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.service;

import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.capacidadcredito.exception.PersonaException;
import mx.gob.imss.dpes.capacidadcredito.model.PersonaRequest;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.PersonaClient;
import mx.gob.imss.dpes.capacidadcredito.rule.CreatePersonaRule;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.interfaces.serviciosdigitales.model.Persona;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author salvador.pocteco
 */
@Provider
public class ConsultarPersonaService extends ServiceDefinition<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest> {

    @Inject
    @RestClient
    private PersonaClient personaClient;

    @Inject
    private CreatePersonaRule rule;

    @Override
    public Message<CapacidadCreditoPersistenciaRequest> execute(Message<CapacidadCreditoPersistenciaRequest> request) throws BusinessException {
        log.log(Level.INFO, "Persona Request General :  {0} ", request.getPayload());
        log.log(Level.INFO, "Persona Request CURP {0} ", request.getPayload().getSolicitud().getCurp());
        PersonaRequest personaRequest = rule.apply(request.getPayload());
        Response respuesta = personaClient.load(personaRequest);

        if (respuesta.getStatus() == 200) {
            Persona persona = respuesta.readEntity(Persona.class);
            request.getPayload().setPersona(persona);
            log.log(Level.INFO, "Los datos de la Persona son {0}", request.getPayload().getPersona());
            return new Message<>(request.getPayload());
        }
        return response(null, ServiceStatusEnum.EXCEPCION, new PersonaException(), null);
    }
}
