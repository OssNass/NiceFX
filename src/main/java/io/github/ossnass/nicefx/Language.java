package io.github.ossnass.nicefx;
import io.github.ossnass.nicefx.types.ControllerInfo;
/**
 * This interface provides the basic skeleton for language file
 *
 * @author Ossama Nasser <ossnass@gmail.com>
 */
public interface Language {

    /**
     * The name of the language in the file
     */
    String LANG_NAME = "LANG.NAME";

    /**
     * The short name of the language like ar for Arabic, en for English
     * Combined with {@link Language#LANG_COUNTRY} to create for language identifier
     */
    String LANG_SHORT = "LANG.SHORT";

    /**
     * The country of the language for setting the correct locale, like SY for Syria, GB for Great Britain
     * Combined with {@link Language#LANG_SHORT} to create for language identifier
     */
    String LANG_COUNTRY = "LANG.COUNTRY";


    /**
     * The title of the stages in javafx, automatically loaded from the language file.
     *
     * This is a formatted string, and need to use the {@link ControllerInfo#getId()} to identify the correct stage name
     */
    String WINDOW_TITLE="TITLE.%s";

}
