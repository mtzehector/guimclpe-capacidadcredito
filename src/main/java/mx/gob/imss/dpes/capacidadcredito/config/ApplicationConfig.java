/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.config;

/**
 * @author antonio
 */

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  /**
   * Do not modify addRestResourceClasses() method.
   * It is automatically populated with
   * all resources defined in the project.
   * If required, comment out calling this method in getClasses().
   */
  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(mx.gob.imss.dpes.basereport.service.ReporteService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.endpoint.CapacidadCreditoCancelacionEndPoint.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.endpoint.CapacidadCreditoEndPoint.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.endpoint.CapacidadCreditoSolicitudEndPoint.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.endpoint.CartaCapacidadCreditoEndPoint.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.endpoint.ReporteCapacidadCreditoEndPoint.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.rule.CreateCapacidadCreditoRule.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.rule.CreateControlFolioRule.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.rule.CreateFolioSolicitudRule.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.rule.CreatePersonaRule.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.rule.CreateSolicitudRule.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.CapacidadCreditoService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.ConsultaReporteService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.ConsultarCapacidadCreditoService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.ConsultarCapacidadCreditoSolicitudService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.ConsultarPersonaService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.ConsultarPrestamosVigentesService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.ConsultarSolicitudService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.ControlService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.CreateEventService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.CreateReporteCartaCapacidadService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.MensajeService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.ObtenerSelloElectronicoService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.PrestamoRecuperacionService.class);
        resources.add(mx.gob.imss.dpes.capacidadcredito.service.ReadCartaCapacidadService.class);
        resources.add(mx.gob.imss.dpes.capacidadcredito.service.SolicitudFolioService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.SolicitudService.class);
    resources.add(mx.gob.imss.dpes.capacidadcredito.service.ValidateCapacidadCreditoService.class);
    resources.add(mx.gob.imss.dpes.common.exception.AlternateFlowMapper.class);
    resources.add(mx.gob.imss.dpes.common.exception.BusinessMapper.class);
    resources.add(mx.gob.imss.dpes.common.rule.MontoTotalRule.class);
        resources.add(mx.gob.imss.dpes.common.rule.PagoMensualRule.class);
    
  }

}