package org.app.gcc.GCCSiteApp;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class GCCBatchUpdateApp 
{
	
	private static final Logger LOGGER =
	        Logger.getLogger(GCCBatchUpdateApp.class.getName());

    public static void main( String[] args ) throws Exception
    {
    	LOGGER.info("**********Starting up GCC Batch Update App*********************");  
    	Logger topLogger = java.util.logging.Logger.getLogger("");
    	Handler consoleHandler = null;
        //see if there is already a console handler
        for (Handler handler : topLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                //found the console handler
                consoleHandler = handler;
                break;
            }
        }
        if (consoleHandler == null) {
            //there was no console handler found, create a new one
            consoleHandler = new ConsoleHandler();
            topLogger.addHandler(consoleHandler);
        }
        //set the console handler to fine:
        consoleHandler.setLevel(java.util.logging.Level.INFO);
    	AppProcessor.processGCCData();
    	LOGGER.info("**********Completed updating GCC Batch Update App*********************");  
    	
    }
}
