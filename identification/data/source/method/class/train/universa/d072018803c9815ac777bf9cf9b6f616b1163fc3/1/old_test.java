    @Test
    public void findKey() throws Exception {
        KeyInfo i1 = new KeyInfo(KeyInfo.PRF.HMAC_SHA256, 1024, null, null);
        AbstractKey pk1 = i1.derivePassword("helluva");
        KeyInfo i2 = new KeyInfo(KeyInfo.PRF.HMAC_SHA256, 1025, null, "the tag".getBytes());
        AbstractKey pk2 = i2.derivePassword("helluva");
        assertEquals(i2.getTag(), pk2.info().getTag());

        KeyRing kr = new KeyRing();
        SymmetricKey sk1 = new SymmetricKey();
        SymmetricKey sk2 = new SymmetricKey();
        AbstractKey privateKey = TestKeys.privateKey(0);
        AbstractKey publicKey1 =TestKeys.privateKey(1).getPublicKey();
        AbstractKey publicKey2 = privateKey.getPublicKey();

        kr.addKeys( sk1, sk2, privateKey, publicKey1, publicKey2, pk1, pk2 );

        kr.addKeys(pk1, pk2);
        Binder b = kr.toBinder();
        KeyRing kr2 = KeyRing.fromBinder(b);

        assertTrue(kr.keySet().contains(pk1));
        assertTrue(kr.keySet().contains(pk2));

        assertEquals(pk2, kr.findKey(i2).toArray()[0]);
        assertEquals(pk2, kr2.findKey(i2).toArray()[0]);

        final Collection<AbstractKey> keys = kr.findKey(i1);
        assertTrue(keys.contains(pk1));
        assertTrue(keys.contains(pk1));
        assertTrue(keys.contains(sk1));
        assertTrue(keys.contains(sk2));
        assertEquals(4, kr2.findKey(i1).size());
    }