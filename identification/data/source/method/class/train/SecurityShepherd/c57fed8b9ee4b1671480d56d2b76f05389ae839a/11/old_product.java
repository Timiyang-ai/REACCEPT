public static MongoClient getMongoDbConnection(String ApplicationRoot){

        //Mongo DB URL from mongo.properties
        String props = Constants.MONGO_DB_PROP;
        MongoClient mongoClient = null;

        // Properties file for mongodb
        String connectionHost = FileInputProperties.readfile(props, "connectionHost");
        String connectionPort = FileInputProperties.readfile(props, "connectionPort");

        try
        {
            mongoClient = new MongoClient(new ServerAddress(connectionHost, Integer.parseInt(connectionPort)));
        }
        catch (NumberFormatException e){ log.fatal("The port in the properties file is not a number: " + e); }
        catch (MongoSocketException e) { log.fatal("Unable to get Mongodb connection (Is it on?): " + e); }
        catch (MongoException e){
            log.fatal("Something went wrong with Mongo: " + e);
            e.printStackTrace();
        }
        catch (Exception e){
            log.fatal("Something went wrong: " + e);
            e.printStackTrace();
        }

        return mongoClient;
    }