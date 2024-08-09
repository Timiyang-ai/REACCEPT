@Test
    public void testEncrypt() {
        context.setSelectedProtocolVersion(ProtocolVersion.TLS13);
        context.setSelectedCipherSuite(CipherSuite.TLS_AES_128_GCM_SHA256);
        context.setEncryptActive(true);
        context.setClientHandshakeTrafficSecret(ArrayConverter
                .hexStringToByteArray("4B63051EABCD514D7CB6D1899F472B9F56856B01BDBC5B733FBB47269E7EBDC2"));
        context.setServerHandshakeTrafficSecret(ArrayConverter
                .hexStringToByteArray("ACC9DB33EE0968FAE7E06DAA34D642B146092CE7F9C9CF47670C66A0A6CE1C8C"));
        context.setConnectionEnd(new ServerConnectionEnd());
        record.setCleanProtocolMessageBytes(ArrayConverter.hexStringToByteArray("080000020000"));
        record.setContentMessageType(ProtocolMessageType.HANDSHAKE);
        record.setPaddingLength(0);
        recordCipher = new RecordAEADCipher(context);
        encryptor = new RecordEncryptor(recordCipher, context);
        encryptor.encrypt(record);
        assertTrue(record.getProtocolMessageBytes().getValue().length == 23);
        assertArrayEquals(record.getProtocolMessageBytes().getValue(),
                ArrayConverter.hexStringToByteArray("1BB3293A919E0D66F145AE830488E8D89BE5EC16688229"));
    }