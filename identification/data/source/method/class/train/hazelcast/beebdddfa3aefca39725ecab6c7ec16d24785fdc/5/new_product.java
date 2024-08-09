public VoteRequest toCandidate() {
        role = RaftRole.CANDIDATE;
        preCandidateState = null;
        leaderState = null;
        candidateState = new CandidateState(majority());
        candidateState.grantVote(localEndpoint);
        setTerm(term + 1);
        persistVote(term, localEndpoint);
        // no need to call persistTerm() since it is called in persistVote()

        return new VoteRequest(localEndpoint, term, log.lastLogOrSnapshotTerm(), log.lastLogOrSnapshotIndex());
    }