package mx.gob.imss.dpes.capacidadcredito.service;

import mx.gob.imss.dpes.capacidadcredito.exception.SelloElectronicoException;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.capacidadcredito.model.DocumentoResponse;
import mx.gob.imss.dpes.capacidadcredito.model.SelloElectronicoRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.SelloElectronicoClient;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ObtenerSelloElectronicoService extends ServiceDefinition<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest> {

  @Inject
  @RestClient
  private SelloElectronicoClient selloElectronicoClient;

  @Override
  public Message<CapacidadCreditoPersistenciaRequest> execute(Message<CapacidadCreditoPersistenciaRequest> request) throws BusinessException {

    SelloElectronicoRequest selloRequest = new SelloElectronicoRequest();
    selloRequest.setFecCreacion(request.getPayload().getSolicitud().getAltaRegistro().toString());
    selloRequest.setFolioNegocio(request.getPayload().getSolicitud().getNumFolioSolicitud());
    selloRequest.setNss(request.getPayload().getSolicitud().getNss());
    selloRequest.setTipoCredito("TipoCredito");

    Response sellar = selloElectronicoClient.create(selloRequest);

    if (sellar.getStatus() == 200) {
      request.getPayload().setDocumento(new DocumentoResponse());
      request.getPayload().getDocumento().setRefSello(sellar.readEntity(String.class));
      return response(request.getPayload(), ServiceStatusEnum.EXITOSO, null, null);
    } else {
      return response(null, ServiceStatusEnum.EXCEPCION, new SelloElectronicoException(), null);
    }
  }
}
