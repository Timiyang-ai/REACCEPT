@Test
    public void testCreateMissingMacByteVectors() {
        List<PaddingVector> vectors = generator.createMissingMacByteVectors(
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, ProtocolVersion.TLS12);
        assertEquals(2, vectors.size());
        int macSize = AlgorithmResolver.getMacAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA).getSize();
        VariableModification modification = ((TrippleVector) vectors.get(0)).getCleanModification();
        ModifiableByteArray array = new ModifiableByteArray();
        array.setModification(modification);
        assertArrayEquals("Validation of clean bytes", new byte[0], array.getValue());

        modification = ((TrippleVector) vectors.get(0)).getPaddingModification();
        array = new ModifiableByteArray();
        array.setModification(modification);
        byte[] expectedPadding = generator
                .createPaddingBytes(ShortPaddingGenerator.DEFAULT_CIPHERTEXT_LENGTH - macSize);
        assertArrayEquals("Validation of used padding", expectedPadding, array.getValue());

        byte[] macToModify = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
        modification = ((TrippleVector) vectors.get(0)).getMacModification();
        array = new ModifiableByteArray();
        array.setOriginalValue(macToModify);
        array.setModification(modification);
        byte[] expectedMac = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
        assertArrayEquals("Validation of the deleted first byte in MAC", expectedMac, array.getValue());

        modification = ((TrippleVector) vectors.get(1)).getMacModification();
        array = new ModifiableByteArray();
        array.setOriginalValue(macToModify);
        array.setModification(modification);
        expectedMac = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
        assertArrayEquals("Validation of the deleted last byte in MAC", expectedMac, array.getValue());
    }