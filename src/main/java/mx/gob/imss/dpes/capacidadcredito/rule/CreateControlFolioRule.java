/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.rule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.common.rule.BaseRule;
import mx.gob.imss.dpes.capacidadcredito.model.ControlFolioModel;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;

/**
 *
 * @author osiris.hernandez
 */
@Provider
public class CreateControlFolioRule extends BaseRule<CapacidadCreditoPersistenciaRequest, ControlFolioModel> {

    @Override
    public ControlFolioModel apply(CapacidadCreditoPersistenciaRequest input) {
        log.log(Level.WARNING, "input para regla {0}", input );
        ControlFolioModel controlFolioModel = new ControlFolioModel();
        DateFormat formatoAnio = new SimpleDateFormat("yyyy");
        String anio = formatoAnio.format(input.getSolicitud().getAltaRegistro());
        controlFolioModel.setNumAnio(anio);
        controlFolioModel.setDelegacion(input.getSolicitud().getDelegacion());
        log.log(Level.WARNING, "output para regla {0}", controlFolioModel );
        return controlFolioModel;
    }

}