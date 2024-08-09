    @Test
    public void buildFederation_incomplete() {
        PendingFederation otherPendingFederation = new PendingFederation(FederationTestUtils.getFederationMembersFromPks(100));

        try {
            otherPendingFederation.buildFederation(Instant.ofEpochMilli(12L), 0L, NetworkParameters.fromID(NetworkParameters.ID_REGTEST));
        } catch (Exception e) {
            Assert.assertEquals("PendingFederation is incomplete", e.getMessage());
            return;
        }
        Assert.fail();
    }