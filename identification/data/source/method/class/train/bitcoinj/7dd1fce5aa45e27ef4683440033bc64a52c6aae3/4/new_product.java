public synchronized void connect() throws PeerException {
        try {
            conn = new NetworkConnection(address, params, bestHeight, 60000, MOBILE_OPTIMIZED);
        } catch (IOException ex) {
            throw new PeerException(ex);
        } catch (ProtocolException ex) {
            throw new PeerException(ex);
        }
    }