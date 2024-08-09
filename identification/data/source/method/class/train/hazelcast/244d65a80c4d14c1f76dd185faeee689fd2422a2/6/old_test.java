    @Test
    public void isKnownEndpoint() {
        for (RaftEndpoint endpoint : members) {
            assertTrue(state.isKnownMember(endpoint));
        }

        assertFalse(state.isKnownMember(newRaftMember(1234)));
        assertFalse(state.isKnownMember(new TestRaftEndpoint(UUID.randomUUID(), localMember.getPort())));
        assertFalse(state.isKnownMember(new TestRaftEndpoint(localMember.getUuid(), 1234)));
    }