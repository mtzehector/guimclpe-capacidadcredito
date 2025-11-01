/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.capacidadcredito.exception.FolioTramiteException;
import mx.gob.imss.dpes.capacidadcredito.model.CapacidadCreditoRequest;
import mx.gob.imss.dpes.capacidadcredito.restclient.CapacidadCreditoClient;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.model.TitularGrupoRequest;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CapacidadCredito;

import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author antonio
 */
@Provider
public class ConsultarCapacidadCreditoService extends ServiceDefinition<CapacidadCreditoRequest, CapacidadCreditoRequest>{

  @Inject
  @RestClient
  private CapacidadCreditoClient capacidadCreditoClient;
  
  @Override
  public Message<CapacidadCreditoRequest> execute(Message<CapacidadCreditoRequest> request) {
    
    TitularGrupoRequest peticion = new TitularGrupoRequest();
    peticion.setNss( request.getPayload().getNss() );
    peticion.setGrupoFamiliar( request.getPayload().getGrupoFamiliar() );
    
    Response load = capacidadCreditoClient.load(peticion);
    if( load.getStatus() == 200 ){
      CapacidadCredito capacidadCredito = load.readEntity(CapacidadCredito.class);
      request.getPayload().setCapacidadCredito(capacidadCredito);
      return request;
    }
    // Excepcion de infra
    return response(null, ServiceStatusEnum.EXCEPCION, new FolioTramiteException(), null );
  }
  
}
