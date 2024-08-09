@Override
    public AnnoConnection getConnection() throws ResourceException {
        log.trace("getConnection()");
        return (AnnoConnection) connectionManager.allocateConnection(mcf, null);
    }