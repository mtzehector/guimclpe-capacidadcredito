/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CapacidadCredito;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;

/**
 *
 * @author osiris.hernandez
 */
public class ResponseCapacidadCredito extends BaseModel{
    @Getter @Setter CapacidadCredito capacidadCredito;
    @Getter @Setter Solicitud solicitud;
}
