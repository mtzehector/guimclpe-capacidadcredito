package mx.gob.imss.dpes.capacidadcredito.service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.dpes.capacidadcredito.model.DocumentoResponse;
import mx.gob.imss.dpes.capacidadcredito.exception.ConsultaReporteException;
import mx.gob.imss.dpes.capacidadcredito.restclient.ReporteCartaPersistenceClient;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class ConsultaReporteService extends ServiceDefinition<Documento, DocumentoResponse> {

  @Inject
  @RestClient
  private ReporteCartaPersistenceClient reporteCartaPersistenceClient;

  @Override
  public Message<DocumentoResponse> execute(Message<Documento> request) throws BusinessException {

    Response load = reporteCartaPersistenceClient.load(request.getPayload().getCveSolicitud().intValue());

    if (load.getStatus() == 200) {
      DocumentoResponse response = load.readEntity(DocumentoResponse.class);
      response.setDescTipoDocumento(TipoDocumentoEnum.CARTA_CAPACIDAD_CREDITO.getDescripcion());
      return new Message<>(response);
    }
    return response(null, ServiceStatusEnum.EXCEPCION, new ConsultaReporteException(), null);
  }
}
