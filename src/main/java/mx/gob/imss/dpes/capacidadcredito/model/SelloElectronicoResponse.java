package mx.gob.imss.dpes.capacidadcredito.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

public class SelloElectronicoResponse extends BaseModel {

  private static final long serialVersionUID = 1L;

  @Getter
  @Setter
  private String sello;
  @Getter
  @Setter
  private String id;
  @Getter
  @Setter
  private String noSerie;
}
