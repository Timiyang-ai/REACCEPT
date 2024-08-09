public void toFollower(int term) {
        role = RaftRole.FOLLOWER;
        leader = null;
        preCandidateState = null;
        leaderState = null;
        candidateState = null;
        completeLeadershipTransfer(null);
        setTerm(term);
        persistTerm();
    }