public void persistVote(int term, Endpoint endpoint) {
        this.lastVoteTerm = term;
        this.votedFor = endpoint;
    }