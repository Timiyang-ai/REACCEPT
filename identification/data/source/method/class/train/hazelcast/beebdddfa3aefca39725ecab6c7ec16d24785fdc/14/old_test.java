    @Test
    public void toLeader_fromCandidate() {
        state.toCandidate(false);

        int term = state.term();
        RaftLog log = state.log();
        log.appendEntries(new LogEntry(term, 1, null), new LogEntry(term, 2, null), new LogEntry(term, 3, null));
        long lastLogIndex = log.lastLogOrSnapshotIndex();

        state.toLeader();

        assertEquals(RaftRole.LEADER, state.role());
        assertEquals(localMember, state.leader());
        assertNull(state.candidateState());

        LeaderState leaderState = state.leaderState();
        assertNotNull(leaderState);

        for (RaftEndpoint endpoint : state.remoteMembers()) {
            FollowerState followerState = leaderState.getFollowerState(endpoint);
            assertEquals(0, followerState.matchIndex());
            assertEquals(lastLogIndex + 1, followerState.nextIndex());
        }

        long[] matchIndices = leaderState.matchIndices();
        assertEquals(state.remoteMembers().size() + 1, matchIndices.length);
        for (long index : matchIndices) {
            assertEquals(0, index);
        }
    }