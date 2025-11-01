/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.rule;

import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.common.rule.BaseRule;

import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.common.enums.TipoCreditoEnum;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CapacidadCredito;

/**
 *
 * @author osiris.hernandez
 */
@Provider
public class CreateCapacidadCreditoRule extends BaseRule<CapacidadCreditoPersistenciaRequest, CapacidadCredito>{

    @Override
    public CapacidadCredito apply(CapacidadCreditoPersistenciaRequest input) {
        CapacidadCredito capacidadcredito = new CapacidadCredito();
        capacidadcredito.setCveSolicitud(input.getSolicitud().getId());
        capacidadcredito.setImpCapacidadFija(input.getCapacidadCredito().getImpCapacidadFija());
        capacidadcredito.setImpCapacidadTotal(input.getCapacidadCredito().getImpCapacidadTotal());
        capacidadcredito.setImpCapacidadVariable(input.getCapacidadCredito().getImpCapacidadVariable());
        capacidadcredito.setTipoCredito(TipoCreditoEnum.NUEVO);
        return capacidadcredito;
    } 
}