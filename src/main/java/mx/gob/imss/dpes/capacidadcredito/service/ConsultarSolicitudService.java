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
import mx.gob.imss.dpes.capacidadcredito.exception.FolioTramiteException;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.ConsultarSolicitudClient;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;
import org.eclipse.microprofile.rest.client.inject.RestClient;
/**
 *
 * @author salvador.pocteco
 */
@Provider
public class ConsultarSolicitudService extends ServiceDefinition<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest> {

    @Inject
    @RestClient
    private ConsultarSolicitudClient consultarSolicitudClient;

    @Override
    public Message<CapacidadCreditoPersistenciaRequest> execute(Message<CapacidadCreditoPersistenciaRequest> request) throws BusinessException {
        log.log(Level.INFO, "Solicitud request general es : {0}", request.getPayload());
        log.log(Level.INFO, "Solicitud Clave Solicitud {0} ", request.getPayload().getCapacidadCredito().getCveSolicitud());
        
        Response respuesta = consultarSolicitudClient.load(request.getPayload().getCapacidadCredito().getCveSolicitud());
        if (respuesta.getStatus() == 200) {
            Solicitud solicitud = respuesta.readEntity(Solicitud.class);
            request.getPayload().setSolicitud(solicitud);

            log.log(Level.INFO, "Los datos de la Solicitud son : {0}", request.getPayload());
            return new Message<>(request.getPayload());
        }
        return response(null, ServiceStatusEnum.EXCEPCION, new CancelacionException(), null);
    }
}
