package mx.gob.imss.dpes.capacidadcredito.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

public class MensajeException extends BusinessException {
	
	private final static String KEY = "msg0001";
	  
	  public MensajeException()  {
	    super(KEY);
	  }

}
