public static DB getMongoDatabase(MongoClient mongoClient)
    {
        String props = Constants.MONGO_DB_PROP;
        DB mongoDb;
        String dbname = FileInputProperties.readfile(props, "databaseName");
        mongoDb = mongoClient.getDB(dbname);

        return mongoDb;
    }