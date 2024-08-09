    @Test
    public void test_grantVote_withoutMajority() {
        RaftEndpoint endpoint = newRaftMember(1000);

        assertTrue(state.grantVote(endpoint));
        assertFalse(state.grantVote(endpoint));

        assertEquals(1, state.voteCount());
        assertFalse(state.isMajorityGranted());
    }