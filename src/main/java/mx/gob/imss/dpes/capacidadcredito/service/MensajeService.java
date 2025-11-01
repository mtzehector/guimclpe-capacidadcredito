package mx.gob.imss.dpes.capacidadcredito.service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import mx.gob.imss.dpes.capacidadcredito.exception.MensajeException;
import mx.gob.imss.dpes.capacidadcredito.model.Mensaje;
import mx.gob.imss.dpes.capacidadcredito.model.MensajeRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.MensajeClient;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;

@Provider
public class MensajeService extends ServiceDefinition< Mensaje,Mensaje> {

	@Inject
	@RestClient
	private MensajeClient mensajeClient;

	@Override
	public Message<Mensaje> execute(Message<Mensaje> request) throws BusinessException {
		
		MensajeRequest peticion = new MensajeRequest();
		peticion.setMensaje( request.getPayload());
		
		Response load = mensajeClient.load(peticion);
	    if( load.getStatus() == 200 ){
	    	Mensaje mensaje = load.readEntity(Mensaje.class);
	        return new Message<>(mensaje);
	    }
		return response(null, ServiceStatusEnum.EXCEPCION, new MensajeException(), null );
	}

}
