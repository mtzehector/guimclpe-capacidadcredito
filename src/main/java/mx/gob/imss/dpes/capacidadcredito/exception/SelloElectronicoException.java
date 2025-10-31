package mx.gob.imss.dpes.capacidadcredito.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

public class SelloElectronicoException extends BusinessException {
  private static final String KEY = "err006";

  public SelloElectronicoException() {super(KEY);}
}
