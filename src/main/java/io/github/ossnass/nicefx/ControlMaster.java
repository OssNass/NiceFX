package io.github.ossnass.nicefx;

/**
 * @author Ossama Nasser <ossnass@gmail.com>
 * 
 */
public class ControlMaster {

    private ControlMaster(){

    }

    private static final ControlMaster INSTANCE = new ControlMaster();

    public static ControlMaster get(){
        return INSTANCE;
    }
}
