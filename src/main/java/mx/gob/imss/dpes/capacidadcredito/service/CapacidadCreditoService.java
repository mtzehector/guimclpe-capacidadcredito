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
import mx.gob.imss.dpes.capacidadcredito.exception.CapacidadCreditoException;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;

import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.CapacidadCreditoPersistenciaClient;
import mx.gob.imss.dpes.capacidadcredito.rule.CreateCapacidadCreditoRule;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CapacidadCredito;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author osiris.hernandez
 */
@Provider
public class CapacidadCreditoService extends ServiceDefinition<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest> {

    @Inject
    @RestClient
    private CapacidadCreditoPersistenciaClient service;

    @Inject
    private CreateCapacidadCreditoRule rule;

    @Override
    public Message<CapacidadCreditoPersistenciaRequest> execute(Message<CapacidadCreditoPersistenciaRequest> request) throws BusinessException {

        CapacidadCredito peticion = rule.apply(request.getPayload());
        log.log(Level.INFO, "Enviando al Back {0}", peticion);
        Response load = service.load(peticion);
        if (load.getStatus() == 200) {
            CapacidadCredito capacidadCredito = load.readEntity(CapacidadCredito.class);
            request.getPayload().setCapacidadCredito(capacidadCredito);
            return new Message<>(request.getPayload());
        }
        return response(null, ServiceStatusEnum.EXCEPCION, new CapacidadCreditoException(), null);
    }

}
