package io.github.ossnass.nicefx.types;

/**
 * This enumeration is used to describe how to initialize a {@link Controller}.
 * 
 * It supports 3 types of initializer:
 * <ol>
 * <li><b>Single_On_Demand:</b> initializes the controller once when requested
 * the first time</li>
 * <li><b>Single_On_Launch:</b> initializes the controller once when the
 * application launches</li>
 * <li><b>Multiple:</b> initializes the controller multiple times on every
 * request</li>
 * </ol>
 * 
 * @author Ossama Nasser <ossnass@gmail.com>
 *
 */
public enum Initialization {
	/**
	 * Initializes the controller once when requested the first time.
	 */
	Single_On_Demand,
	/**
	 * Initializes the controller once when the application launches.
	 */
	Single_On_Launch,
	/**
	 * Initializes the controller multiple times on every request.
	 */
	Multiple
}