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
import mx.gob.imss.dpes.capacidadcredito.exception.CancelacionException;

import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.CapacidadCreditoSolicitudClient;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CapacidadCredito;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author antonio
 */
@Provider
public class ConsultarCapacidadCreditoSolicitudService extends ServiceDefinition<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest> {

    @Inject
    @RestClient
    private CapacidadCreditoSolicitudClient capacidadCreditoSolicitudClient;

    @Override
    public Message<CapacidadCreditoPersistenciaRequest> execute(Message<CapacidadCreditoPersistenciaRequest> request) throws BusinessException {
        log.log(Level.INFO, "Request General : {0} ", request.getPayload());
        log.log(Level.INFO, "Request Clave Solicitud : {0} ", request.getPayload().getCapacidadCredito().getCveSolicitud());

        Response respuesta = capacidadCreditoSolicitudClient.load(request.getPayload().getCapacidadCredito().getCveSolicitud());
        if (respuesta.getStatus() == 200) {
            CapacidadCredito capacidadCreditoModel = respuesta.readEntity(CapacidadCredito.class);
            request.getPayload().setCapacidadCredito(capacidadCreditoModel);
            
            log.log(Level.INFO, "Los datos de la capacidad de credito son : {0}", request.getPayload().getCapacidadCredito());
            return new Message<>(request.getPayload());
        }
        return response(null, ServiceStatusEnum.EXCEPCION, new CancelacionException(), null);
    }
}
