    @Test
    public void deserializeFederation_wrongListSize() {
        byte[] serialized = RLP.encodeList(RLP.encodeElement(new byte[0]), RLP.encodeElement(new byte[0]));

        try {
            BridgeSerializationUtils.deserializeFederation(serialized, NetworkParameters.fromID(NetworkParameters.ID_REGTEST));
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("Invalid serialized Federation"));
        }
    }