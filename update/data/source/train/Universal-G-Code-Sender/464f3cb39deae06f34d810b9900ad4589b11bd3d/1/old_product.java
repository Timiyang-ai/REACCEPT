protected boolean openCommPort(String name, int baud) throws Exception {
        if (conn == null) {
            conn = ConnectionFactory.getConnection();
            logger.info("Connecting to controller using class: " + conn.getClass().getSimpleName());
        }

        if (conn != null) {
            conn.setCommunicator(this);
        }
        
        if (conn==null) {
            throw new Exception(Localization.getString("communicator.exception.port") + ": "+name);
        }
        
        // Handle all events in a single thread.
        this.eventThread.start();

        //open it
        return conn.openPort(name, baud);
    }