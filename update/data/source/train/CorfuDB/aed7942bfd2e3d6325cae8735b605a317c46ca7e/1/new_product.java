public void shutdown() {
        setState(ServerState.SHUTDOWN);
        //Verify that this called by subclasses
    }