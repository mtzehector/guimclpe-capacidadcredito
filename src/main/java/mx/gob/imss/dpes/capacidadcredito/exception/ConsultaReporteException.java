package mx.gob.imss.dpes.capacidadcredito.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

public class ConsultaReporteException extends BusinessException {

  private static final String KEY = "err008";

  public ConsultaReporteException() {
    super(KEY);
  }
}
