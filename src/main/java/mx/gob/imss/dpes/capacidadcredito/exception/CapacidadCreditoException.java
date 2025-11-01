/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

/**
 *
 * @author osiris.hernandez
 */
public class CapacidadCreditoException extends BusinessException{
  private final static String KEY = "err004";
  
  public final static String DELEGACION_NULL = "delegacionNull";
  public final static String CURP_NULL = "curpNull";
  public final static String NSS_NULL = "nssNull";
  public final static String GRUPO_FAMILIAR_NULL = "grupoFamiliarNull";
  public final static String IMP_CAPACIDAD_FIJA_NULL = "impCapacidadFijaNull";
  public final static String IMP_CAPACIDAD_TOTAL_NULL = "impCapacidadTotalNull";
  public final static String IMP_CAPACIDAD_VARIABLE_NULL = "impCapacidadVariableNull";
  
  public CapacidadCreditoException() {
    super(KEY);
  }
  
  public CapacidadCreditoException(String causa) {
    super(causa);
  }
  
}
