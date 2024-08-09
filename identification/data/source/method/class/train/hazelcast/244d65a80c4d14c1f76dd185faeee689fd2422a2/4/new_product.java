public void persistVote(int term, RaftEndpoint endpoint) {
        assert this.term == term;
        assert this.votedFor == null;
        this.votedFor = endpoint;
        persistTerm();
    }