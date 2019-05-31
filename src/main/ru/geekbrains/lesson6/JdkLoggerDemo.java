package ru.geekbrains.lesson6;

import java.io.IOException;
import java.util.logging.*;

public class JdkLoggerDemo {

    private static final Logger logger = Logger.getLogger(JdkLoggerDemo.class.getName());

    public static void main(String[] args) throws IOException {
//        logger.setLevel(Level.ALL);
//        logger.getParent().setLevel(Level.ALL);
//        logger.getParent().getHandlers()[0].setLevel(Level.ALL);
//
//        FileHandler fileHandler = new FileHandler("log_file.log", true);
//        fileHandler.setFormatter(new Formatter() {
//            @Override
//            public String format(LogRecord record) {
//                return String.format("! %s %d %s%n", record.getLevel(), record.getMillis(), record.getMessage());
//            }
//        });
//        logger.addHandler(fileHandler);
//
//        logger.setFilter(new Filter() {
//            @Override
//            public boolean isLoggable(LogRecord record) {
//                return record.getMessage().contains("War");
//            }
//        });

        LogManager.getLogManager().readConfiguration(JdkLoggerDemo.class.getClassLoader()
                .getResourceAsStream("jul.properties"));

        logger.log(Level.SEVERE, "Severe message");
        logger.severe("Another severe message");
        logger.warning("Warning message");
        logger.info("Info message");
        logger.fine("Fine message");
    }
}
