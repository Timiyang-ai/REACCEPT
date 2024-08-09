@Override
    public void disconnect() throws Exception {
        this.stop = true;
        this.eventThread.interrupt();
        connection.closePort();
    }