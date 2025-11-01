/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.rule;

import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.capacidadcredito.model.PersonaRequest;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.common.rule.BaseRule;

/**
 *
 * @author salvador.pocteco
 */
@Provider
public class CreatePersonaRule extends BaseRule<CapacidadCreditoPersistenciaRequest, PersonaRequest>{

    @Override
    public PersonaRequest apply(CapacidadCreditoPersistenciaRequest input) {
        PersonaRequest personaRequest = new PersonaRequest();
        
        personaRequest.setCurp(input.getSolicitud().getCurp());
        
        return personaRequest;
    }
    
    
}
