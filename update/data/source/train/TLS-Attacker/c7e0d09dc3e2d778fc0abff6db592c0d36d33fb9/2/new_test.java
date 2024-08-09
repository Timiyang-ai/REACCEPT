@Test
    public void testDecrypt12() throws NoSuchAlgorithmException {
        RandomHelper.setRandom(new BadRandom(new Random(0), null));
        context.getConfig().addConnectionEnd(new ClientConnectionEnd());
        context.setSelectedCipherSuite(CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256);
        context.setSelectedProtocolVersion(ProtocolVersion.TLS12);
        context.setClientRandom(ArrayConverter
                .hexStringToByteArray("03c08c3460b420bb3851d9d47acb933dbe70399bf6c92da33af01d4fb770e98c"));
        context.setServerRandom(ArrayConverter
                .hexStringToByteArray("78f0c84e04d3c23cad94aad61ccae23ce79bcd9d2d6953f8ccbe0e528c63a238"));
        context.setMasterSecret(ArrayConverter
                .hexStringToByteArray("F81015161244782B3541E6020140556E4FFEA98C57FCF6CEC172CD8B577DC73CCDE4B724E07DB8687DDF327CD8A68891"));
        byte[] data = ArrayConverter
                .hexStringToByteArray("45DCB1853201C59037AFF4DFE3F442B7CDB4DB1348894AE76E251F4491A6F5F859B2DE12879C6D86D4BDC83CAB854E33EF5CC51B25942E64EC6730AB1DDB5806E900B7B0C32D9BFF59C0F01334C0F673");

        cipher = new RecordBlockCipher(context, KeySetGenerator.generateKeySet(context));
        byte[] plaintext = cipher.decrypt(data);
        byte[] correctPlaintext = ArrayConverter
                .hexStringToByteArray("7F1F9E3AA2EAD435ED42143C54D81FEDAC85A400AF369CABFA1B77EBB3647B534FB8447306D14FE610F897EBE455A43ED47140370DB20BF3181067641D20E425");
        assertArrayEquals(plaintext, correctPlaintext);
    }