    @Test
    public void deserializeElection_emptyOrNull() throws Exception {
        AddressBasedAuthorizer mockAuthorizer = mock(AddressBasedAuthorizer.class);
        ABICallElection election;
        election = BridgeSerializationUtils.deserializeElection(null, mockAuthorizer);
        Assert.assertEquals(0, election.getVotes().size());
        election = BridgeSerializationUtils.deserializeElection(new byte[]{}, mockAuthorizer);
        Assert.assertEquals(0, election.getVotes().size());
    }