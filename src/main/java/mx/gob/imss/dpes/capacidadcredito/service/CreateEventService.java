package mx.gob.imss.dpes.capacidadcredito.service;

import mx.gob.imss.dpes.capacidadcredito.exception.SolicitudException;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.IdentityBaseModel;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.capacidadcredito.restclient.EventoClient;
import mx.gob.imss.dpes.common.enums.EventEnum;
import mx.gob.imss.dpes.interfaces.evento.model.Evento;
@Provider
public class CreateEventService extends ServiceDefinition<IdentityBaseModel<Long>, IdentityBaseModel<Long>> {

  @Inject
  @RestClient
  private EventoClient client;

  @Override
  public Message<IdentityBaseModel<Long>> execute(Message<IdentityBaseModel<Long>> request) throws BusinessException {

    log.log(Level.INFO, "Solicitando el evento : {0}", request.getPayload());
    Evento evento = new Evento();
    evento.setId(request.getPayload().getId());
    evento.setEvent(EventEnum.CREAR_SOLICITUD_CAPACIDAD_CREDITO);
    Response event = client.create(evento);
    if (event.getStatus() == 200) {
      return request;
    }
    return response(null, ServiceStatusEnum.EXCEPCION, new SolicitudException(), null);
  }
}
