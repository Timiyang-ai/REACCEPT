    @Test
    public void extractPublicKey() throws Exception {
        byte[] data = "Hello world".getBytes();
        PrivateKey k = TestKeys.privateKey(3);
        byte [] signature = ExtendedSignature.sign(k, data);
        PublicKey pubKey = k.getPublicKey();
        ExtendedSignature es = ExtendedSignature.verify(pubKey, signature, data);
        assertNotNull(es);
        assertAlmostSame(es.getCreatedAt(), ZonedDateTime.now());
        assertEquals(ExtendedSignature.keyId(k), ExtendedSignature.keyId(pubKey));
        assertEquals(ExtendedSignature.keyId(k), ExtendedSignature.extractKeyId(signature));
        assertNotNull(es.getPublicKey());
        assertEquals(pubKey, es.getPublicKey());
    }