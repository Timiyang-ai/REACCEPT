public boolean isKnownEndpoint(RaftEndpoint endpoint) {
        return lastGroupMembers.isKnownEndpoint(endpoint);
    }