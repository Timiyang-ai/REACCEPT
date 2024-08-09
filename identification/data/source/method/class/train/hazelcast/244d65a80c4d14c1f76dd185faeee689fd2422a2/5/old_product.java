public void persistVote(int term, RaftEndpoint endpoint) {
        this.lastVoteTerm = term;
        this.votedFor = endpoint;
    }