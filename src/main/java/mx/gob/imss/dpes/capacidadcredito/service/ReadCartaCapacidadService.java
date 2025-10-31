package mx.gob.imss.dpes.capacidadcredito.service;

import java.text.DecimalFormat;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CartaCapacidadCredito;
import mx.gob.imss.dpes.capacidadcredito.model.ReporteCartaCapacidad;
import mx.gob.imss.dpes.capacidadcredito.restclient.DocumentoClient;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.exception.RecursoNoExistenteException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import javax.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Provider
public class ReadCartaCapacidadService extends ServiceDefinition<ReporteCartaCapacidad, ReporteCartaCapacidad> {

    @Inject
    @RestClient
    private DocumentoClient documentoClient;

    @Override
    public Message<ReporteCartaCapacidad> execute(Message<ReporteCartaCapacidad> request) throws BusinessException {

        log.log(Level.INFO, "Buscando el resumen de la solicitud: {0}", request.getPayload().getIdSolicitud());

        Documento documento = new Documento();
        documento.setCveSolicitud(request.getPayload().getIdSolicitud());
        documento.setTipoDocumento(TipoDocumentoEnum.CARTA_CAPACIDAD_CREDITO);
        Response response = documentoClient.loadRefDocumento(documento);

        if (response.getStatus() == 200) {
            CartaCapacidadCredito cartaCapacidadCredito = response.readEntity(CartaCapacidadCredito.class);

            DecimalFormat formatter = new DecimalFormat("#,###.00");
            cartaCapacidadCredito.setImpCapacidadFija(
                    formatter.format(Double.parseDouble(cartaCapacidadCredito.getImpCapacidadFija()))
            );
            cartaCapacidadCredito.setImpCapacidadVariable(
                    formatter.format(Double.parseDouble(cartaCapacidadCredito.getImpCapacidadVariable()))
            );
            
            if (cartaCapacidadCredito.getImpCapacidadVariable().equals(".00")) {
                cartaCapacidadCredito.setImpCapacidadVariable("0.00");
            }
            
            cartaCapacidadCredito.setImpCapacidadTotal(
                    formatter.format(Double.parseDouble(cartaCapacidadCredito.getImpCapacidadTotal()))
            );

            request.getPayload().setCartaCapacidadCredito(cartaCapacidadCredito);
            return request;
        }

        return response(null, ServiceStatusEnum.EXCEPCION, new RecursoNoExistenteException(), null);
    }
}
