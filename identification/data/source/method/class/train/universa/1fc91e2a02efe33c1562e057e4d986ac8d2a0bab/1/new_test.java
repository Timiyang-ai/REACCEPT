    @Test
    public void fromExponents() throws Exception {
        RSAKeyPair rsaPair = RSAKeyPair.fromExponents(rsaSpec.e, rsaSpec.p, rsaSpec.q);
        assertArrayEquals(rsaPair.n, rsaSpec.n);
        assertArrayEquals(rsaPair.e, rsaSpec.e);
        assertArrayEquals(rsaPair.d, rsaSpec.d);
        assertArrayEquals(rsaPair.p, rsaSpec.p);
        assertArrayEquals(rsaPair.q, rsaSpec.q);
        assertArrayEquals(rsaPair.dP, rsaSpec.dP);
        assertArrayEquals(rsaPair.dQ, rsaSpec.dQ);
        assertArrayEquals(rsaPair.qInv, rsaSpec.qInv);
    }