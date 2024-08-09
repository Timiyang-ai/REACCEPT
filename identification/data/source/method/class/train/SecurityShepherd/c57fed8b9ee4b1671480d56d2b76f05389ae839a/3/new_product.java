public static MongoClient getMongoDbConnection(String ApplicationRoot, MongoCredential credential){

        //Mongo DB URL out of mongo.properties
        String props = Constants.MONGO_DB_PROP;
        MongoClient mongoClient = null;

        // Properties file for all of mongo
        String connectionHost = FileInputProperties.readfile(props, "connectionHost");
        String connectionPort = FileInputProperties.readfile(props, "connectionPort");

        try
        {
            mongoClient = new MongoClient(new ServerAddress(connectionHost, Integer.parseInt(connectionPort)),
                    Arrays.asList(credential));
        }
        catch (MongoSocketException e)
        {
            log.fatal("Unable to get Mongodb connection (Is it on?): " + e);
            e.printStackTrace();
        }
        catch (MongoException e){
            log.fatal("Unable to get Mongodb connection (Is it on?): " + e);
            e.printStackTrace();
        }
        catch (Exception e){
            log.fatal("Something went wrong with Mongo: " + e);
            e.printStackTrace();
        }

        return mongoClient;
    }