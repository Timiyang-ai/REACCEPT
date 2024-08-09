public synchronized void connect() throws PeerException {
        try {
            conn = new TCPNetworkConnection(address, params, CONNECT_TIMEOUT_MSEC, false, versionMessage);
        } catch (IOException ex) {
            throw new PeerException(ex);
        } catch (ProtocolException ex) {
            throw new PeerException(ex);
        }
    }