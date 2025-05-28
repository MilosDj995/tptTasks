package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerManager {

    private static final Logger logger = LoggerFactory.getLogger(LoggerManager.class);

    public static void log(String text) {
        logger.info(text);
    }

    public static void logError(String text) {
        logger.error(text);
    }

    public static void logWarn(String text) {
        logger.warn(text);
    }

    public static void logDebug(String text) {
        logger.debug(text);
    }

    public static void logTrace(String text) {
        logger.trace(text);
    }
}
