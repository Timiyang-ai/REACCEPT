public void connect() {
        try {
            conn = new NetworkConnection(address, params, bestHeight, 60000);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ProtocolException ex) {
            throw new RuntimeException(ex);
        }
    }