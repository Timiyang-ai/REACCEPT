public boolean grantVote(RaftEndpoint address) {
        return voters.add(address);
    }