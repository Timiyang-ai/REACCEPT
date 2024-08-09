@Test
    public void testParseMessageAction() {

        handler.initializeProtocolMessage();

        byte[] inputBytes = { HandshakeMessageType.CERTIFICATE_VERIFY.getValue(), 0x00, 0x00, 0x09 };
        byte[] sigHashAlg = { HashAlgorithm.SHA512.getValue(), SignatureAlgorithm.RSA.getValue() };
        inputBytes = ArrayConverter.concatenate(inputBytes, sigHashAlg, new byte[] { 0x00, 0x05 }, new byte[] { 0x25,
                0x26, 0x27, 0x28, 0x29 });
        int endPointer = handler.parseMessageAction(inputBytes, 0);
        CertificateVerifyMessage message = (CertificateVerifyMessage) handler.getProtocolMessage();

        assertNotNull("Confirm endPointer is not 'NULL'", endPointer);
        assertEquals("Confirm actual message length", endPointer, 13);
        assertEquals("Confirm message type", HandshakeMessageType.CERTIFICATE_VERIFY, message.getHandshakeMessageType());
        assertArrayEquals("Confirm SignatureAndHashAlgorithm type", sigHashAlg, message.getSignatureHashAlgorithm()
                .getValue());
        assertTrue("Confirm Signature Length", message.getSignatureLength().getValue() == 5);
        assertTrue("Confirm Signature",
                Arrays.equals(message.getSignature().getValue(), new byte[] { 0x25, 0x26, 0x27, 0x28, 0x29 }));

    }