public void persistVote(int term, RaftMember endpoint) {
        this.lastVoteTerm = term;
        this.votedFor = endpoint;
    }