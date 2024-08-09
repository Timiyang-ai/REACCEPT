public static MongoClient getMongoDbConnection(String ApplicaitonRoot){

        //Mongo DB URL out of mongo.properties
        String props = Constants.MONGO_DB_PROP;
        MongoClient mongoClient = null;

        // Properties file for all of mongo
        String connectionHost = FileInputProperties.readfile(props, "connectionHost");
        String connectionPort = FileInputProperties.readfile(props, "connectionPort");
        String databaseName = FileInputProperties.readfile(props, "databaseName");

        try
        {
            mongoClient = new MongoClient(new ServerAddress(connectionHost, Integer.parseInt(connectionPort)));
        }
        catch (MongoException e){
            log.fatal("Unable to get Mongodb connection (Is it on?): " + e);
            e.printStackTrace();
            closeConnection(mongoClient);
        }
        catch (Exception e){
            log.fatal("Something went wrong with Mongo: " + e);
            e.printStackTrace();
            closeConnection(mongoClient);
        }

        return mongoClient;
    }