package mx.gob.imss.dpes.capacidadcredito.model;

import lombok.Data;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;

@Data
public class DocumentoResponse extends Documento {
  private String descTipoDocumento;
}
