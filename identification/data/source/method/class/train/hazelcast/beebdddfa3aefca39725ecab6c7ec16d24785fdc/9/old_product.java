public void toFollower(int term) {
        role = RaftRole.FOLLOWER;
        leader = null;
        leaderState = null;
        candidateState = null;
        this.term = term;
    }