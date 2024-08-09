public boolean isKnownEndpoint(RaftEndpoint endpoint) {
        return members.contains(endpoint);
    }