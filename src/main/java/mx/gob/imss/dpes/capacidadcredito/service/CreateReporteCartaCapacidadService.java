package mx.gob.imss.dpes.capacidadcredito.service;

import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CartaCapacidadCredito;
import mx.gob.imss.dpes.capacidadcredito.model.ReporteCartaCapacidad;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.exception.RecursoNoExistenteException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.Reporte;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.basereport.service.ReporteService;
import mx.gob.imss.dpes.common.service.ServiceDefinition;

import javax.inject.Inject;
import java.util.logging.Level;
import javax.ws.rs.ext.Provider;
@Provider
public class CreateReporteCartaCapacidadService
    extends ServiceDefinition<ReporteCartaCapacidad, ReporteCartaCapacidad> {

  @Inject
  private ReporteService service;

  @Override
  public Message<ReporteCartaCapacidad> execute(Message<ReporteCartaCapacidad> request) throws BusinessException {
    log.log( Level.INFO, "Armando el reporte: {0}", request.getPayload());



    Reporte<CartaCapacidadCredito> reporte = request.getPayload().getReporte();
    reporte.setRuta("/reports/CapacidadCredito.jasper");    
    reporte.setPassword( request.getPayload().getCartaCapacidadCredito().getCurp().substring(4, 10) );
    log.log(Level.INFO,"PWD: {0}", request.getPayload().getCartaCapacidadCredito().getCurp().substring(4, 10) );
    reporte.getBeans().add( request.getPayload().getCartaCapacidadCredito() );

    Message<Reporte> response = service.execute( new Message( reporte ) );
    if ( !Message.isException(response) ) {
      reporte.setPdf( response.getPayload().getPdf() );
      return request;
    }
    return response(null, ServiceStatusEnum.EXCEPCION, new RecursoNoExistenteException(), null);
  }
}
