public void toLeader() {
        role = RaftRole.LEADER;
        leader(localEndpoint);
        candidateState = null;
        leaderState = new LeaderState(remoteMembers, log.lastLogIndex());
    }