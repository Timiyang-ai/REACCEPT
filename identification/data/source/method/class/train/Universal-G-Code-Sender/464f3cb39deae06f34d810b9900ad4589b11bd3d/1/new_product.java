protected boolean openCommPort(ConnectionDriver connectionDriver, String name, int baud) throws Exception {
        if (conn == null) {
            String url = connectionDriver.getProtocol() + name + ":" + baud;
            conn = ConnectionFactory.getConnection(url);
            logger.info("Connecting to controller using class: " + conn.getClass().getSimpleName() + " with url " + url);
        }

        if (conn != null) {
            conn.setCommunicator(this);
        }
        
        if (conn==null) {
            throw new Exception(Localization.getString("communicator.exception.port") + ": " + name);
        }
        
        // Handle all events in a single thread.
        this.eventThread.start();

        //open it
        return conn.openPort();
    }