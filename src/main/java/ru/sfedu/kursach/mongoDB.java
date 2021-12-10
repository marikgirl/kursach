package ru.sfedu.kursach;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class mongoDB {

    private static final Logger log = LogManager.getLogger(Main.class);

    public boolean connectToDB(){
        try{
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB database = mongoClient.getDB("myMongoDb");
        }
        catch(Exception e){
            log.info("MongoDB connection fail");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }



}
