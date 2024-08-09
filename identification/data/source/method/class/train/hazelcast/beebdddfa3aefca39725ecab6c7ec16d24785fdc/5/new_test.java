    @Test
    public void toCandidate_fromFollower() {
        int term = 23;
        state.toFollower(term);

        state.toCandidate(false);
        assertEquals(RaftRole.CANDIDATE, state.role());
        assertNull(state.leaderState());
        assertEquals(term + 1, state.term());
        assertEquals(localMember, state.votedFor());

        CandidateState candidateState = state.candidateState();
        assertNotNull(candidateState);
        assertEquals(state.majority(), candidateState.majority());
        assertFalse(candidateState.isMajorityGranted());
        assertEquals(1, candidateState.voteCount());
    }