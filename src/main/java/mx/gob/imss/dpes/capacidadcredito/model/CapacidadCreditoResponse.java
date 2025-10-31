/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.model;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;
/**
 *
 * @author antonio
 */
public class CapacidadCreditoResponse extends BaseModel{
  @Getter @Setter private String impCapacidadFija;
  @Getter @Setter private String impCapacidadVariable;
  @Getter @Setter private String impCapacidadTotal;
}
