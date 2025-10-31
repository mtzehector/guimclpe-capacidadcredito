/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.endpoint;

import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.capacidadcredito.model.ResponseCapacidadCredito;
import mx.gob.imss.dpes.capacidadcredito.service.CapacidadCreditoService;
import mx.gob.imss.dpes.capacidadcredito.service.ConsultarPersonaService;
import mx.gob.imss.dpes.capacidadcredito.service.ControlService;
import mx.gob.imss.dpes.capacidadcredito.service.CreateEventService;
import mx.gob.imss.dpes.capacidadcredito.service.PrestamoRecuperacionService;
import mx.gob.imss.dpes.capacidadcredito.service.SolicitudFolioService;
import mx.gob.imss.dpes.capacidadcredito.service.SolicitudService;
import mx.gob.imss.dpes.capacidadcredito.service.ValidateCapacidadCreditoService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.model.IdentityBaseModel;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.interfaces.serviciosdigitales.model.Persona;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;

/**
 *
 * @author antonio
 */
@Path("/cartaCapacidadCredito")
@RequestScoped
public class CartaCapacidadCreditoEndPoint extends BaseGUIEndPoint<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest, BaseModel> {

    @Inject
    SolicitudService solicitudService;
    @Inject
    ControlService controlService;
    @Inject
    SolicitudFolioService solicitudFolioService;
    @Inject
    CapacidadCreditoService capacidadCreditoService;
    @Inject
    private ConsultarPersonaService consultarPersonaService;
    @Inject
    private ValidateCapacidadCreditoService validate;

    @Inject
    private CreateEventService eventService;
    @Inject
    PrestamoRecuperacionService prestamoRecuperacionService;
    @Path("/insertaCapacidad")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Generar una Carta de Libranza de capacidad de credito",
            description = "Generar una Carta de Libranza de capacidad de credito")
    @Override
    public Response create(CapacidadCreditoPersistenciaRequest request) throws BusinessException {

        ServiceDefinition[] steps = {
            validate,
            solicitudService,
            capacidadCreditoService,
            controlService,
            solicitudFolioService,
            prestamoRecuperacionService
        };
        Message<CapacidadCreditoPersistenciaRequest> response = validate.executeSteps(steps, new Message<>(request));

        if (!Message.isException(response)) {
            IdentityBaseModel<Long> model = new IdentityBaseModel<>();
            model.setId(response.getPayload().getSolicitud().getId());
            eventService.execute(new Message<>(model));
        }

        ResponseCapacidadCredito r = new ResponseCapacidadCredito();
        Message<ResponseCapacidadCredito> respuesta = new Message<>(r);

        respuesta.getPayload().setCapacidadCredito(response.getPayload().getCapacidadCredito());
        respuesta.getPayload().setSolicitud(response.getPayload().getSolicitud());
        return toResponse(respuesta);
    }

    @Path("/consultaPersona")
    @GET
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener los datos de la persona pensionada",
            description = "Obtener los datos de la persona pensionada")
    @Override
    public Response load(CapacidadCreditoPersistenciaRequest request) throws BusinessException {

        Message<CapacidadCreditoPersistenciaRequest> response = consultarPersonaService.execute(new Message<>(request));

        if (response.getHeader().getStatus() != ServiceStatusEnum.EXITOSO) {
            Message<Persona> personaResponse;
            Persona persona = response.getPayload().getPersona();
            personaResponse = new Message<>(persona);

            return toResponse(personaResponse);
        }
        return toResponse(response);
    }
}
