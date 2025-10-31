/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.service;

import java.util.Set;
import java.util.logging.Level;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.capacidadcredito.exception.CapacidadCreditoException;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CapacidadCredito;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;

/**
 *
 * @author osiris.hernandez
 */
@Provider
public class ValidateCapacidadCreditoService extends
        ServiceDefinition<CapacidadCreditoPersistenciaRequest, CapacidadCreditoPersistenciaRequest> {

    @Override
    public Message<CapacidadCreditoPersistenciaRequest> execute(
            Message<CapacidadCreditoPersistenciaRequest> request) throws
            BusinessException {

        try ( ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {

            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Solicitud>> violationsSolicitud = validator.
                    validate(request.getPayload().getSolicitud());
            if (!violationsSolicitud.isEmpty()) {
                throw new CapacidadCreditoException(violationsSolicitud.iterator().next().
                        getMessage());
            }

            Set<ConstraintViolation<CapacidadCredito>> violations = validator.
                    validate(request.getPayload().getCapacidadCredito());

            if (!violations.isEmpty()) {
                throw new CapacidadCreditoException(violations.iterator().next().
                        getMessage());
            }

        }
        return request;
    }

}
