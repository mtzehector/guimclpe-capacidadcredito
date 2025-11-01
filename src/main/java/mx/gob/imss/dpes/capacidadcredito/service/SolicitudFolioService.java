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
import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.capacidadcredito.exception.SolicitudFolioException;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.SolicitudPersistenciaClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import mx.gob.imss.dpes.capacidadcredito.rule.CreateFolioSolicitudRule;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;

/**
 *
 * @author osiris.hernandez
 */
@Provider
public class SolicitudFolioService extends ServiceDefinition<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest> {

    @Inject
    @RestClient
    private SolicitudPersistenciaClient solicitudPersistenciaClient;

    @Inject
    private CreateFolioSolicitudRule rule;

    @Override
    public Message<CapacidadCreditoPersistenciaRequest> execute(Message<CapacidadCreditoPersistenciaRequest> request) throws BusinessException {
        log.log(Level.INFO, "Paso 4");
        Solicitud solicitud = rule.apply(request.getPayload());

        Response load = solicitudPersistenciaClient.updateFolio(solicitud);
        if (load.getStatus() == 200) {
            Solicitud sol = load.readEntity(Solicitud.class);
            request.getPayload().setSolicitud(sol);
            return new Message<>(request.getPayload());
        }
        return response(null, ServiceStatusEnum.EXCEPCION, new SolicitudFolioException(), null);
    }

}
