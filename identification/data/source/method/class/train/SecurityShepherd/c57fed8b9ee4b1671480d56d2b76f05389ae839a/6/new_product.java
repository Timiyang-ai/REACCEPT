public static DB getMongoDatabase(MongoClient mongoClient)
    {
        String props = Constants.MONGO_DB_PROP;
        DB mongoDb = null;
        String dbname = FileInputProperties.readfile(props, "databaseName");
        try {
            mongoDb = mongoClient.getDB(dbname);
        }
        catch (MongoSocketException e) { log.fatal("Unable to get Mongodb connection (Is it on?): " + e); }
        catch (MongoTimeoutException e) { log.fatal("Unable to get Mongodb connection (Is it on?): " + e); }
        catch (MongoException e){ log.fatal("Something went wrong with Mongo: " + e); e.printStackTrace(); }
        catch (Exception e){log.fatal("Something went wrong: " + e); e.printStackTrace(); }

        return mongoDb;
    }