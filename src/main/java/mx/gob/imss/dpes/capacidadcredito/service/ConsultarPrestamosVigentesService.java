/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.capacidadcredito.exception.FolioTramiteException;
import mx.gob.imss.dpes.capacidadcredito.model.PrestamosVigentesRequest;
import mx.gob.imss.dpes.common.enums.EstadoFolioEnum;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import mx.gob.imss.dpes.capacidadcredito.restclient.PrestamosVigentesClient;

/**
 *
 * @author antonio
 */
@Provider
public class ConsultarPrestamosVigentesService extends ServiceDefinition<Pensionado, Pensionado>{

  @Inject
  @RestClient
  private PrestamosVigentesClient prestamosVigentesClient;
  
  @Override
  public Message<Pensionado> execute(Message<Pensionado> request) {
        
    PrestamosVigentesRequest peticion = new PrestamosVigentesRequest();
    peticion.setPensionado(request.getPayload());
    List<EstadoFolioEnum> estadosActivos = Arrays.asList(  EstadoFolioEnum.INICIADO, EstadoFolioEnum.PENDIENTE_MONTO_CAPTURAR );
    peticion.setEstados(estadosActivos);
    log.log(Level.INFO, "Invocando el servicio, {0}", peticion);
    
    Response load = prestamosVigentesClient.load(peticion);
    
    log.log(Level.INFO, "Respuesta del servicio, {0}", load.getStatusInfo().getReasonPhrase());
    
    if( load.getStatus() == 200 ){
      Long numeroPrestamos = load.readEntity(Long.class);
      if( numeroPrestamos > 0 ){
        return response(null, ServiceStatusEnum.EXCEPCION, new FolioTramiteException(), null );
      }
      return request;
    }
    
    return response(null, ServiceStatusEnum.EXCEPCION, new FolioTramiteException(), null );
  }
  
}
