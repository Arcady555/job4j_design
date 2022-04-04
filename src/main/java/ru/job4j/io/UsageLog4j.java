package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte byteVar = 5;
        short shortVar = 120;
        char charVar = '5';
        int intVar = 33;
        long longVar = 922337200;
        double doubleVar = 67.579987568951389;
        float floatVar = 78.78F;
        boolean booleanVar = false;

        LOG.debug("User info byteVar : {}, shortVar  : {}, charVar : {}, intVar : {},"
                + " longVar : {}, doubleVar : {}, floatVar : {}, booleanVar : {}",
                byteVar, shortVar, charVar, intVar,
                longVar, doubleVar, floatVar, booleanVar);
    }
}