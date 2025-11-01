/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.rule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.common.rule.BaseRule;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;

/**
 *
 * @author osiris.hernandez
 */
@Provider
public class CreateFolioSolicitudRule extends BaseRule<CapacidadCreditoPersistenciaRequest, Solicitud> {

    @Override
    public Solicitud apply(CapacidadCreditoPersistenciaRequest input) {
        
        Solicitud solicitudModel = new Solicitud();
        solicitudModel.setConsecutivo(input.getConsecutivo().getNumConsecutivo());
        solicitudModel.setDelegacion(input.getSolicitud().getDelegacion());
        solicitudModel.setId(input.getSolicitud().getId());
        solicitudModel.setAltaRegistro( input.getSolicitud().getAltaRegistro() );
        
        return solicitudModel;
    }
}

