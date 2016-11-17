package com.sid.practice;

import com.sid.practice.impl.PeopleProcessorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by Sid Saddala on 4/26/16.
 */
public class AppMain
{
    private static final Logger LOG = LogManager.getLogger(AppMain.class);
    public static void main( String[] args )
    {
        LOG.info(" *** People Process BEGIN *** ");

        PeopleProcessor peopleProcessor = new PeopleProcessorImpl();
        peopleProcessor.processFile("src/main/resources/PersonData.csv");

        LOG.info("*** People Process END *** ");


    }
}
