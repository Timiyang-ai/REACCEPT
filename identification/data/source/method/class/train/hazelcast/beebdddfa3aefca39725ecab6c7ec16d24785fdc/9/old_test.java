    @Test
    public void toFollower_fromCandidate() {
        state.toCandidate(false);

        int term = 23;
        state.toFollower(term);

        assertEquals(term, state.term());
        assertEquals(RaftRole.FOLLOWER, state.role());
        assertNull(state.leader());
        assertNull(state.leaderState());
        assertNull(state.candidateState());
    }