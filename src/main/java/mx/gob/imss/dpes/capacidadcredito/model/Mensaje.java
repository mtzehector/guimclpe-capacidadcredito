package mx.gob.imss.dpes.capacidadcredito.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

public class Mensaje extends BaseModel{
	
	@Getter @Setter private String claveMensaje;	
	@Getter @Setter private String mensaje;

}
