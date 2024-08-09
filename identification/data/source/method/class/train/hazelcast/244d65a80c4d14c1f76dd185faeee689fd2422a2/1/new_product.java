public boolean isKnownMember(RaftMember endpoint) {
        return lastGroupMembers.isKnownMember(endpoint);
    }