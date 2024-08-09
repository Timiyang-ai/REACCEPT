public void toLeader() {
        role = RaftRole.LEADER;
        leader(localEndpoint);
        preCandidateState = null;
        candidateState = null;
        leaderState = new LeaderState(lastGroupMembers.remoteMembers(), log.lastLogOrSnapshotIndex());
    }