package mx.gob.imss.dpes.capacidadcredito.restclient;

import mx.gob.imss.dpes.interfaces.documento.model.Documento;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reporte")
@RegisterRestClient
public interface ReporteCartaPersistenceClient {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response create(Documento documento);

  @GET
  @Path("/{cveSolicitud}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response load(@PathParam("cveSolicitud") Integer cveSolicitud);

  @PUT
  @Path("/actualizar")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response update(Documento documento);
}