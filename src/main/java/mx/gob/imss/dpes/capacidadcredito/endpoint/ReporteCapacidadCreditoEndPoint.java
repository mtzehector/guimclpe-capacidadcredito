/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.endpoint;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import mx.gob.imss.dpes.capacidadcredito.model.ReporteCartaCapacidad;
import mx.gob.imss.dpes.capacidadcredito.service.CreateReporteCartaCapacidadService;
import mx.gob.imss.dpes.capacidadcredito.service.ReadCartaCapacidadService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;

/**
 *
 * @author antonio
 */
@Path("/reporteCapacidadCredito")
@RequestScoped
public class ReporteCapacidadCreditoEndPoint extends BaseGUIEndPoint<BaseModel, BaseModel, BaseModel>{

  @Inject ReadCartaCapacidadService readCartaCapacidadService;
  @Inject CreateReporteCartaCapacidadService createReporteCartaCapacidadService;

  @GET
  @Path("/{idSolicitud}")
  @Produces("application/pdf")
  @Operation(summary = "Generar el reporte de resumen de simulacion",
      description = "Generar el reporte de resumen de simulacion")
  public Response create(@PathParam("idSolicitud") Long idSolicitud) throws BusinessException {

    ReporteCartaCapacidad request = new ReporteCartaCapacidad();
    request.setIdSolicitud(idSolicitud);

    ServiceDefinition[] steps = {readCartaCapacidadService, createReporteCartaCapacidadService};
    Message<ReporteCartaCapacidad> response
        = createReporteCartaCapacidadService.executeSteps(steps, new Message<>(request));

    if( !Message.isException(response) ){
      return Response.ok(response.getPayload().getReporte().getPdf()).header("Content-Disposition",
          "attachment; filename=ReporteCapacidadCredito.pdf").build();
    }
    return toResponse(response);


  }

}
