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
import mx.gob.imss.dpes.capacidadcredito.exception.SolicitudException;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.SolicitudPersistenciaClient;
import mx.gob.imss.dpes.capacidadcredito.rule.CreateSolicitudRule;
import mx.gob.imss.dpes.common.enums.OrigenSolicitudEnum;
import mx.gob.imss.dpes.common.enums.TipoEstadoSolicitudEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;

/**
 *
 * @author osiris.hernandez
 */
@Provider
public class SolicitudService extends ServiceDefinition<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest> {

    @Inject
    @RestClient
    private SolicitudPersistenciaClient service;

    @Inject
    private CreateSolicitudRule rule;

    @Override
    public Message<CapacidadCreditoPersistenciaRequest> execute(Message<CapacidadCreditoPersistenciaRequest> request) throws BusinessException {
        Solicitud solicitud = rule.apply(request.getPayload());

        log.log(Level.INFO, "Request hacia SolicitudBack 1: {0}", solicitud);
        Response load = service.load(solicitud);
        if (load.getStatus() == 200) {
            Solicitud sol = load.readEntity(Solicitud.class);
            request.getPayload().setSolicitud(sol);
            return new Message<>(request.getPayload());
        }
        return response(null, ServiceStatusEnum.EXCEPCION, new SolicitudException(), null);
    }

}
