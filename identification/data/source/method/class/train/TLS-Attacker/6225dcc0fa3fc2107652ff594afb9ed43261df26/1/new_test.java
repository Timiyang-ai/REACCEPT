@Test
    public void testAdjustTLSContext() {
        DHEServerKeyExchangeMessage message = new DHEServerKeyExchangeMessage();
        message.setP(BigInteger.TEN.toByteArray());
        message.setG(BigInteger.ONE.toByteArray());
        message.setSerializedPublicKey(new byte[] { 0, 1, 2, 3 });
        message.prepareComputations();
        message.getComputations().setPrivateKey(new BigInteger("123"));
        message.getComputations().setPremasterSecret(new byte[] { 0, 1, 2, 3 });
        // TODO assert master secret was computed correctly
        // message.getComputations().setMasterSecret(new byte[] { 4, 5, 6 });
        handler.adjustTLSContext(message);
        assertNull(context.getPreMasterSecret());
        // assertNull(context.getMasterSecret());
    }