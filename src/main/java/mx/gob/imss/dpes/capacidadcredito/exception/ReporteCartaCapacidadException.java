package mx.gob.imss.dpes.capacidadcredito.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

public class ReporteCartaCapacidadException extends BusinessException {

  private static final String KEY = "err007";

  public ReporteCartaCapacidadException() {super(KEY);}
}
