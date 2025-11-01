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
import mx.gob.imss.dpes.capacidadcredito.exception.SolicitudException;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.PrestamoClient;
import mx.gob.imss.dpes.capacidadcredito.restclient.SolicitudPersistenciaClient;
import mx.gob.imss.dpes.common.enums.TipoEstadoSolicitudEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.prestamo.model.PrestamoRecuperacion;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author juan.garfias
 */
@Provider
public class PrestamoRecuperacionService extends
        ServiceDefinition<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest> {

    @Inject
    @RestClient
    private PrestamoClient service;

    @Inject
    @RestClient
    private SolicitudPersistenciaClient solicitudClient;

    @Override
    public Message<CapacidadCreditoPersistenciaRequest> execute(Message<CapacidadCreditoPersistenciaRequest> request) throws BusinessException {
        log.log(Level.INFO, "Step 6 por Capacidad de crédito. ");
        log.log(Level.INFO, "Request Prestamo Recuperacion Back {0}", request.getPayload().getPrestamoRecuperacion());

        if (request.getPayload().getPrestamoRecuperacion() != null) {

            request.getPayload().getPrestamoRecuperacion().setSolicitud(
                    request.getPayload().getSolicitud().getId()
            );

            request.getPayload().getPrestamoRecuperacion().setImpRealPrestamo(
                    request.getPayload().getPrestamoRecuperacion().getCanMontoSol()
            );
            Double nuevoDescuento;
            nuevoDescuento = request.getPayload().getPrestamoRecuperacion().getCanDescuentoMensual() + request.getPayload().getCapacidadCredito().getImpCapacidadFija();
            request.getPayload().getPrestamoRecuperacion().setImpSumaDescMensual(nuevoDescuento);

            Solicitud s = request.getPayload().getSolicitud();
            s.setEstadoSolicitud(TipoEstadoSolicitudEnum.PENDIENTE_MONTO_LIQUIDAR);
            solicitudClient.updateEstado(s);

            Response load = service.prestamoEnRecuperacion(request.getPayload().getPrestamoRecuperacion());
            if (load.getStatus() == 200) {
                PrestamoRecuperacion prestamoRecuperacionOut = load.readEntity(PrestamoRecuperacion.class);
                request.getPayload().setPrestamoRecuperacion(prestamoRecuperacionOut);
                return new Message<>(request.getPayload());
            } else {
                return response(null, ServiceStatusEnum.EXCEPCION, new CapacidadCreditoException(), null);
            }

        } else {
            return request;
        }

    }
}
