/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcredito.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;
import mx.gob.imss.dpes.interfaces.serviciosdigitales.model.Persona;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CapacidadCredito;
import mx.gob.imss.dpes.interfaces.prestamo.model.PrestamoRecuperacion;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;

/**
 * @author osiris.hernandez
 */
public class CapacidadCreditoPersistenciaRequest extends BaseModel {

    @Getter @Setter CapacidadCredito capacidadCredito;
    @Getter @Setter Solicitud solicitud;
    @Getter @Setter ControlFolioModel consecutivo;
    @Getter @Setter Persona persona;
    @Getter @Setter PersonaRequest personaRequest;
    @Getter @Setter Documento documento;
    @Getter @Setter PrestamoRecuperacion prestamoRecuperacion = new PrestamoRecuperacion();

}
