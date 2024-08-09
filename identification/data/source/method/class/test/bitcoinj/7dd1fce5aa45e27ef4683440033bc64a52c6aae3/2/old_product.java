public void connect() {
        try {
            conn = new NetworkConnection(address, params, bestHeight, 60000);
        } catch (IOException ex) {
            log.error("while trying to open connection", ex);
            throw new RuntimeException(ex);
        } catch (ProtocolException ex) {
            log.error("while trying to negotiate connection", ex);
            throw new RuntimeException(ex);
        }
    }