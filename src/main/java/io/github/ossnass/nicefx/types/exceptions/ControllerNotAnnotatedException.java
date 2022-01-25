package io.github.ossnass.nicefx.types.exceptions;

import io.github.ossnass.nicefx.types.Controller;
import io.github.ossnass.nicefx.types.ControllerInfo;

import java.io.Serial;

/**
 * This class represents an exception thrown if and only if the {@link Controller} is not annotated with {@link ControllerInfo}
 * @author Ossama Nasser <ossnass@gmail.com>
 *
 */
public class ControllerNotAnnotatedException extends RuntimeException{
    /**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 2247702364519019248L;

	public ControllerNotAnnotatedException(String controllerName){
        super(String.format("Conteroller %s is not annotated with ControllerInfo annotation",controllerName));
    }
}