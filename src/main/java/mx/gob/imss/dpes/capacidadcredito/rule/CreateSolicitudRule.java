/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.rule;

import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.common.enums.OrigenSolicitudEnum;
import mx.gob.imss.dpes.common.enums.TipoEstadoSolicitudEnum;
import mx.gob.imss.dpes.common.rule.BaseRule;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoPersistenciaRequest;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;

/**
 *
 * @author osiris.hernandez
 */
@Provider
public class CreateSolicitudRule extends BaseRule<CapacidadCreditoPersistenciaRequest, Solicitud>{

  final String PENSIONADO = "pensionado";
  @Override
  public Solicitud apply(CapacidadCreditoPersistenciaRequest input) {
    
    input.getSolicitud().setRefTrabajador(PENSIONADO);
    input.getSolicitud().setOrigenSolictud( OrigenSolicitudEnum.CARTA_CAPACIDAD_CREDITO );
    input.getSolicitud().setEstadoSolicitud(TipoEstadoSolicitudEnum.INICIADO);
    input.getSolicitud().setCurp(input.getSolicitud().getCurp());
    input.getSolicitud().setNss(input.getSolicitud().getNss());
    input.getSolicitud().setDelegacion(input.getSolicitud().getDelegacion());
    input.getSolicitud().setSubDelegacion(input.getSolicitud().getSubDelegacion());
    input.getSolicitud().setGrupoFamiliar(input.getSolicitud().getGrupoFamiliar());
    return input.getSolicitud();
  }
  
}