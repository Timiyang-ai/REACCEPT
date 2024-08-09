@Test
    public void testEncryptTls10() {
        context.getConfig().setConnectionEndType(ConnectionEndType.CLIENT);
        context.setSelectedCipherSuite(CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA);
        context.setSelectedProtocolVersion(ProtocolVersion.TLS10);
        context.setClientRandom(ArrayConverter
                .hexStringToByteArray("03c08c3460b420bb3851d9d47acb933dbe70399bf6c92da33af01d4fb770e98c"));
        context.setServerRandom(ArrayConverter
                .hexStringToByteArray("78f0c84e04d3c23cad94aad61ccae23ce79bcd9d2d6953f8ccbe0e528c63a238"));
        context.setMasterSecret(ArrayConverter
                .hexStringToByteArray("F81015161244782B3541E6020140556E4FFEA98C57FCF6CEC172CD8B577DC73CCDE4B724E07DB8687DDF327CD8A68891"));
        byte[] data = ArrayConverter
                .hexStringToByteArray("1400000CCE92FBEC9131F48A63FED31F71573F726479AA9108FB86A4FA16BC1D5CB5753003030303");

        cipher = new RecordBlockCipher(context);
        byte[] ciphertext = cipher.encrypt(data);
        byte[] correctCiphertext = ArrayConverter
                .hexStringToByteArray("C34B06D54CDE2A5AF25EE0AE1896F6F149720FA9EC205C6629B2C7F52A7F3A72931E351D4AD26E23");
        assertArrayEquals(ciphertext, correctCiphertext);

        data = ArrayConverter.hexStringToByteArray("54657374EDE63C0E2BDAB2875D35FFC30ED4C327F7B54CCB0707070707070707");
        ciphertext = cipher.encrypt(data);
        correctCiphertext = ArrayConverter
                .hexStringToByteArray("7829006A6B93FA6348E1074E58CCEFA9EBBEA3202ABA82F9A2B7BC26D187AF08");
        assertArrayEquals(ciphertext, correctCiphertext);
    }