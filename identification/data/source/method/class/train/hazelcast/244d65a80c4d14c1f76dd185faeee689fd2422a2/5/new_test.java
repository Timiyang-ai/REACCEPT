    @Test
    public void persistVote() {
        int term = 13;
        state.toFollower(term);
        state.persistVote(term, localMember);

        assertEquals(term, state.term());
        assertEquals(localMember, state.votedFor());
    }