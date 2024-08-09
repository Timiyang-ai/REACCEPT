public void toFollower(int term) {
        role = RaftRole.FOLLOWER;
        leader = null;
        preCandidateState = null;
        leaderState = null;
        candidateState = null;
        if (leadershipTransferState != null) {
            assert leadershipTransferState.term() < term;
            completeLeadershipTransfer(null);
        }
        setTerm(term);
        persistTerm();
    }