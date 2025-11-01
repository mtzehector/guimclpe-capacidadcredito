/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.endpoint;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoRequest;
import mx.gob.imss.dpes.capacidadcredito.service.ConsultarCapacidadCreditoService;
import mx.gob.imss.dpes.capacidadcredito.service.ConsultarPrestamosVigentesService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CapacidadCredito;
import org.eclipse.microprofile.openapi.annotations.Operation;

/**
 *
 * @author antonio
 */
@Path("/capacidadCredito")
@RequestScoped
public class CapacidadCreditoEndPoint extends BaseGUIEndPoint<CapacidadCredito, Pensionado, CapacidadCredito> {

  @Inject
  ConsultarCapacidadCreditoService consultarCapacidadCreditoService;
  @Inject
  ConsultarPrestamosVigentesService consultarPrestamosVigentesService;

  @GET
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Obtener la capacidad de credito del pensionado",
          description = "Obtener la capacidad de credito del pensionado")
  public Response load(CapacidadCreditoRequest pensionado) throws BusinessException {

    ServiceDefinition[] steps = { consultarPrestamosVigentesService,
      consultarCapacidadCreditoService };
    
    Message<CapacidadCreditoRequest> response = consultarCapacidadCreditoService.
            executeSteps(steps, new Message<>(pensionado));

    Message<CapacidadCredito> msg1 = new Message<>(response.getPayload().getCapacidadCredito());
    return toResponse(msg1);
  }

}
