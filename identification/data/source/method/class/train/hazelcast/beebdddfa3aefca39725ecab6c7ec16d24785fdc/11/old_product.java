public VoteRequest toCandidate() {
        role = RaftRole.CANDIDATE;
        leaderState = null;
        candidateState = new CandidateState(majority());
        candidateState.grantVote(localEndpoint);
        persistVote(incrementTerm(), localEndpoint);

        return new VoteRequest(localEndpoint, term, log.lastLogTerm(), log.lastLogIndex());
    }