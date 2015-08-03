package personXML;

import java.io.Serializable;

/**
 * Created by Oxana on 11/8/14.
 */

public enum TerminalState implements Serializable
{
    INIT_DEVICE,
    INIT_TERMINAL,
    WAIT,
    INPUT_PROCESS,
    ERROR
}


