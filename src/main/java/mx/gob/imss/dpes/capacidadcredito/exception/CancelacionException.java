/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

/**
 *
 * @author salvador.pocteco
 */
public class CancelacionException extends BusinessException{
  private final static String KEY = "err010";

    public CancelacionException() {
        super(KEY);
    }
}
