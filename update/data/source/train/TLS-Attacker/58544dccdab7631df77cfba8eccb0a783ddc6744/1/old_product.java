List<PaddingVector> createMissingMacByteVectors(CipherSuite suite, ProtocolVersion version) {
        List<PaddingVector> vectorList = new LinkedList<>();
        int macSize = AlgorithmResolver.getMacAlgorithm(version, suite).getSize();
        byte[] padding = createPaddingBytes(DEFAULT_CIPHERTEXT_LENGTH - macSize + 1 - 1);
        vectorList.add(new TrippleVector(new ByteArrayExplicitValueModification(new byte[0]),
                new ByteArrayDeleteModification(0, 1), new ByteArrayExplicitValueModification(padding)));
        padding = createPaddingBytes(DEFAULT_CIPHERTEXT_LENGTH - macSize + 1 - 1);
        padding[0] ^= 0x80; // flip first padding byte last bit
        vectorList.add(new TrippleVector(new ByteArrayExplicitValueModification(new byte[0]),
                new ByteArrayDeleteModification(0, 1), new ByteArrayExplicitValueModification(padding)));
        padding = createPaddingBytes(DEFAULT_CIPHERTEXT_LENGTH - macSize + 1 - 1);
        padding[(DEFAULT_CIPHERTEXT_LENGTH - macSize + 1) / 2] ^= 0x8; // flip middle padding byte
        // middle bit
        vectorList.add(new TrippleVector(new ByteArrayExplicitValueModification(new byte[0]),
                new ByteArrayDeleteModification(0, 1), new ByteArrayExplicitValueModification(padding)));
        padding = createPaddingBytes(DEFAULT_CIPHERTEXT_LENGTH - macSize + 1 - 1);
        padding[(DEFAULT_CIPHERTEXT_LENGTH - macSize + 1 - 1)] ^= 0x01; // flip last padding byte first
        // bit
        vectorList.add(new TrippleVector(new ByteArrayExplicitValueModification(new byte[0]),
                new ByteArrayDeleteModification(0, 1), new ByteArrayExplicitValueModification(padding)));
        return vectorList;
    }