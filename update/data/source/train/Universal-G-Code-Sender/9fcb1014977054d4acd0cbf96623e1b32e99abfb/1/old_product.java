protected boolean openCommPort(ConnectionDriver connectionDriver, String name, int baud) throws Exception {
        if (connection == null) {
            String url = connectionDriver.getProtocol() + name + ":" + baud;
            connection = ConnectionFactory.getConnection(url);
            logger.info("Connecting to controller using class: " + connection.getClass().getSimpleName() + " with url " + url);
        }

        if (connection != null) {
            connection.addListener(this);
        }

        if (connection == null) {
            throw new Exception(Localization.getString("communicator.exception.port") + ": " + name);
        }

        // Handle all events in a single thread.
        this.eventThread.start();

        //open it
        return connection.openPort();
    }