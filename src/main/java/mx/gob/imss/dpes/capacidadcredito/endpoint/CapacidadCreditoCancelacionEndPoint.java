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
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.capacidadcredito.service.ConsultarCapacidadCreditoSolicitudService;
import mx.gob.imss.dpes.capacidadcredito.service.ConsultarPersonaService;
import mx.gob.imss.dpes.capacidadcredito.service.ConsultarSolicitudService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.model.BaseModel;
import org.eclipse.microprofile.openapi.annotations.Operation;

/**
 *
 * @author salvador.pocteco
 */
@Path("/capacidadCreditoCancelacion")
@RequestScoped
public class CapacidadCreditoCancelacionEndPoint extends BaseGUIEndPoint<BaseModel, CapacidadCreditoPersistenciaRequest, BaseModel> {

    @Inject
    private ConsultarCapacidadCreditoSolicitudService consultarCapacidadCreditoSolicitudService;
    
    @Inject
    private ConsultarSolicitudService consultarSolicitudService;
    
    @Inject
    private ConsultarPersonaService consultarPersonaService;
    
    @GET
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener los datos de la capacidad de creditos, solicitud y persona para su cancelacion",
            description = "Obtener los datos de la capacidad de creditos, solicitud y persona para su cancelacion")
    @Override
    public Response load(CapacidadCreditoPersistenciaRequest capacidadCreditoPersistenciaRequest) throws BusinessException {

        ServiceDefinition[] steps = {consultarCapacidadCreditoSolicitudService, consultarSolicitudService, consultarPersonaService};
        Message<CapacidadCreditoPersistenciaRequest> response = consultarCapacidadCreditoSolicitudService.executeSteps(steps, new Message<>(capacidadCreditoPersistenciaRequest));

        return toResponse(response);
    }
}
