@Override
    public AnnoConnection getConnection() throws ResourceException {
        log.finest("getConnection()");
        return (AnnoConnection) connectionManager.allocateConnection(mcf, null);
    }