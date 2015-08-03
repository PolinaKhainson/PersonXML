package personXML;


import com.utils.encoding.UTF8Control;
import personXML.components.MainScreen;

import java.util.ResourceBundle;


/**
 * Created by Oxana on 11/8/14.
 */
public class Terminal {

    public static MainScreen mainScreen=new MainScreen();
    public final static String pathToLocale = "resources.Locale";
    public static ResourceBundle resourceBundle=getResourceBundle();
    public static TerminalState terminalState;

    private Terminal() {
        terminalState=TerminalState.WAIT;


    }
    public static void init(){
        mainScreen.showTerminalInit("Please wait, while system load...");
    }

    public TerminalState getTerminalState() {
        return terminalState;
    }

    public static void setTerminalState(TerminalState terminalState) {
        Terminal.terminalState = terminalState;
    }



    public static MainScreen getMainScreen() {
        return mainScreen;
    }

    public static Terminal getInstance(){
       return new Terminal();
    }
    public static ResourceBundle getResourceBundle() {

        resourceBundle= ResourceBundle.getBundle(pathToLocale,new UTF8Control());
        return resourceBundle;
    }

}
