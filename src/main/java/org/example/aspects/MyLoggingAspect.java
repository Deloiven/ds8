package org.example.aspects;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class MyLoggingAspect {
    private final Logger logger = Logger.getLogger(MyLoggingAspect.class.getName());

    public MyLoggingAspect() {
        try {
            FileHandler fileHandler = new FileHandler("myApp.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occur in FileHandler.", e);
        }
    }

    @AfterReturning(value = "@annotation(TrackUserAction))", returning = "returnedValue")
    public void log(JoinPoint joinPoint, Object returnedValue) {
        // Получаем информацию о методе
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] methodArgs = joinPoint.getArgs();


        // ANSI Escape Code для сброса цвета
        String ANSI_RESET = "\u001B[0m";

        String myColorStart = "\u001B[43;30m";
        String myColor1 = "\u001B[32m";
        String myColor2 = "\u001B[35m";
        String myColor3 = "\u001B[36m";
        String myColor4 = "\u001B[34m";
        String myColorEND = "\u001B[43;30m";

        // Выводим нужную информацию в консоль в виде loga
        logger.info(myColorStart + "Информация логирования: " + ANSI_RESET);
        logger.info(myColor1 + "Вызванный метод: " + ANSI_RESET + methodName);
        logger.info(myColor2 + "Класс: " + ANSI_RESET + className);
        logger.info(myColor3 + "Аргументы: " + ANSI_RESET + Arrays.toString(methodArgs));
        logger.info(myColor4 + "Метод отработал и вернул значение: " + ANSI_RESET + returnedValue);
        logger.info(myColorEND + "Конец блока логирования. " + ANSI_RESET);
    }

}