    @Test
    public void deserializePendingFederation_invalidFederationMember() {
        byte[] serialized = RLP.encodeList(
                RLP.encodeList(RLP.encodeElement(new byte[0]), RLP.encodeElement(new byte[0]))
        );

        try {
            BridgeSerializationUtils.deserializePendingFederation(serialized);
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid serialized FederationMember"));
        }
    }