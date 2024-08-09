public static MongoClient getMongoDbConnection(String ApplicationRoot, MongoCredential credential){

        //Mongo DB URL out of mongo.properties
        String props = Constants.MONGO_DB_PROP;
        MongoClient mongoClient = null;

		// Properties file for all of mongo

		String connectionHost = "";
		String connectionPort = "";
		String connectTimeout = "";
		String socketTimeout = "";
		String serverSelectionTimeout = "";

		try {

			connectionHost = FileInputProperties.readfile(props, "connectionHost");
			connectionPort = FileInputProperties.readfile(props, "connectionPort");
			connectTimeout = FileInputProperties.readfile(props, "connectTimeout");
			socketTimeout = FileInputProperties.readfile(props, "socketTimeout");
			serverSelectionTimeout = FileInputProperties.readfile(props, "serverSelectionTimeout");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		MongoClientOptions.Builder optionsBuilder = MongoClientOptions.builder();
		optionsBuilder.connectTimeout(Integer.parseInt(connectTimeout));
        optionsBuilder.socketTimeout(Integer.parseInt(socketTimeout));
        optionsBuilder.serverSelectionTimeout(Integer.parseInt(serverSelectionTimeout));
        MongoClientOptions mongoOptions = optionsBuilder.build();

        try
        {
            mongoClient = new MongoClient(new ServerAddress(connectionHost, Integer.parseInt(connectionPort)),
                    Arrays.asList(credential), mongoOptions);

            log.debug("Connection Host: " + connectionHost );
            log.debug("Connection Port: " + Integer.parseInt(connectionPort));
            log.debug("Connection Creds: " + Arrays.asList(credential));
        }
		catch (NumberFormatException e) {
			log.fatal("The port in the properties file is not a number: " + e);
			throw new RuntimeException(e); 

		} catch (MongoSocketException e) {
			log.fatal("Unable to get Mongodb connection (Is it on?): " + e);
			throw new RuntimeException(e); 

		} catch (MongoTimeoutException e) {
			log.fatal("Unable to get Mongodb connection (Is it on?): " + e);
			throw new RuntimeException(e); 

		} catch (MongoException e) {
			log.fatal("Something went wrong with Mongo: " + e);
			e.printStackTrace();
			throw new RuntimeException(e); 

		} catch (Exception e) {
			log.fatal("Something went wrong: " + e);
			e.printStackTrace();
			throw new RuntimeException(e); 

		}

        log.debug("Mongo Client: " + mongoClient);

        return mongoClient;
    }