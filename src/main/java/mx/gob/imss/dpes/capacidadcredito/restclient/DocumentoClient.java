/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.restclient;

import mx.gob.imss.dpes.interfaces.documento.model.Documento;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author antonio
 */
@RegisterRestClient
@Path("/documento")
public interface DocumentoClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Documento request);
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/refDocBoveda")
    public Response update(Documento request);

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response load(@PathParam("id") Long id);
    
    @POST
    @Path("/bySolicitudAndTipo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response bySolicitudAndTipo(Documento request);
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/refDocumento/{id}")
    Response loadRefDocumento(@PathParam("id") Long id);
    
    @POST
    @Path("/refDocumento/bySolicitudAndTipo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response loadRefDocumento(Documento request);
}
