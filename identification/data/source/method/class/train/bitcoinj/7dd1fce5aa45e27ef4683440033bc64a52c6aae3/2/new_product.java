public synchronized void connect() throws PeerException {
        try {
            conn = new TCPNetworkConnection(params, versionMessage);
            conn.connect(address, CONNECT_TIMEOUT_MSEC);
        } catch (IOException ex) {
            throw new PeerException(ex);
        } catch (ProtocolException ex) {
            throw new PeerException(ex);
        }
    }