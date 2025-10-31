package mx.gob.imss.dpes.capacidadcredito.restclient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import mx.gob.imss.dpes.capacidadcredito.model.MensajeRequest;

@Path("/prueba/json")
@RegisterRestClient
public interface MensajeClient {
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response load(MensajeRequest request);

}
