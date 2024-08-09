@Test
    public void testDecrypt() throws NoSuchAlgorithmException {
        context.setSelectedProtocolVersion(ProtocolVersion.TLS13);
        context.setSelectedCipherSuite(CipherSuite.TLS_AES_128_GCM_SHA256);
        context.setClientHandshakeTrafficSecret(ArrayConverter
                .hexStringToByteArray("4B63051EABCD514D7CB6D1899F472B9F56856B01BDBC5B733FBB47269E7EBDC2"));
        context.setServerHandshakeTrafficSecret(ArrayConverter
                .hexStringToByteArray("ACC9DB33EE0968FAE7E06DAA34D642B146092CE7F9C9CF47670C66A0A6CE1C8C"));
        context.setConnection(new OutboundConnection());
        record.setProtocolMessageBytes(ArrayConverter
                .hexStringToByteArray("1BB3293A919E0D66F145AE830488E8D89BE5EC16688229"));
        context.setActiveClientKeySetType(Tls13KeySetType.HANDSHAKE_TRAFFIC_SECRETS);
        recordCipher = new RecordAEADCipher(context, KeySetGenerator.generateKeySet(context));
        decryptor = new RecordDecryptor(recordCipher, context);
        decryptor.decrypt(record);
        // assertTrue(record.getContentMessageType() ==
        // ProtocolMessageType.HANDSHAKE);
        assertTrue(record.getCleanProtocolMessageBytes().getValue().length == 6);
        assertArrayEquals(record.getCleanProtocolMessageBytes().getValue(),
                ArrayConverter.hexStringToByteArray("080000020000"));
    }