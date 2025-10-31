/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CapacidadCredito;

/**
 *
 * @author antonio
 */
public class CapacidadCreditoRequest extends BaseModel{
  @Getter @Setter String nss;
  @Getter @Setter String grupoFamiliar;  
  @Getter @Setter CapacidadCredito capacidadCredito;
}
