protected void closeCommPort() throws Exception {
        this.stop = true;
        this.eventThread.interrupt();
        connection.closePort();
    }