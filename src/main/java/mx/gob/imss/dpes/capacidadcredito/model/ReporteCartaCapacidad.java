package mx.gob.imss.dpes.capacidadcredito.model;

import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CartaCapacidadCredito;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.Reporte;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;


public class ReporteCartaCapacidad extends Documento {

  @Getter @Setter CartaCapacidadCredito cartaCapacidadCredito = new CartaCapacidadCredito();
	@Getter @Setter Long idSolicitud;
  @Getter @Setter String xml;
	@Getter @Setter Reporte<CartaCapacidadCredito> reporte = new Reporte<>();
}
