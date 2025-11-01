package mx.gob.imss.dpes.capacidadcredito.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

public class CartaCapacidadParseException extends BusinessException {

  private static final String KEY ="err009";

  public CartaCapacidadParseException() {
    super(KEY);
  }
}
