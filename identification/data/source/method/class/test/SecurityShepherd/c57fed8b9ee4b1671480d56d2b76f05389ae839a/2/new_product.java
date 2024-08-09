public static MongoClient getMongoDbConnection(String ApplicationRoot){

		// Mongo DB URL from mongo.properties
		String props = Constants.MONGO_DB_PROP;

		// Properties file for mongodb
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
		} catch (IOException | PropertyNotFoundException e) {
			throw new RuntimeException(e);
		}

		MongoClientOptions.Builder optionsBuilder = MongoClientOptions.builder();
		optionsBuilder.connectTimeout(Integer.parseInt(connectTimeout));
		optionsBuilder.socketTimeout(Integer.parseInt(socketTimeout));
		optionsBuilder.serverSelectionTimeout(Integer.parseInt(serverSelectionTimeout));
		MongoClientOptions mongoOptions = optionsBuilder.build();

		try (MongoClient mongoClient = new MongoClient(
				new ServerAddress(connectionHost, Integer.parseInt(connectionPort)), mongoOptions)) {
			
			log.debug("Mongo Client: " + mongoClient);
			return mongoClient;
			
		} catch (NumberFormatException e) {
			log.fatal("The port in the properties file is not a number: " + e);
			throw new RuntimeException(e);

		} catch (MongoSocketOpenException e) {
			log.fatal("Mongo Doesn't seem to be running: " + e);
			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (MongoSocketException e) {
			log.fatal("Unable to get Mongodb connection (Is it on?): " + e);
			throw new RuntimeException(e);

		} catch (MongoException e) {
			log.fatal("Something went wrong with Mongo: " + e);
			throw new RuntimeException(e);

		}

    }