List<PaddingVector> createMissingMacByteVectors(CipherSuite suite, ProtocolVersion version) {
        List<PaddingVector> vectorList = new LinkedList<>();
        int macSize = AlgorithmResolver.getMacAlgorithm(version, suite).getSize();
        byte[] padding = createPaddingBytes(DEFAULT_CIPHERTEXT_LENGTH - macSize);
        // Missing first MAC byte because of overlong valid padding
        vectorList.add(new TrippleVector(new ByteArrayExplicitValueModification(new byte[0]),
                new ByteArrayDeleteModification(0, 1), new ByteArrayExplicitValueModification(padding)));
        // Missing last MAC byte because of overlong valid padding
        vectorList.add(new TrippleVector(new ByteArrayExplicitValueModification(new byte[0]),
                new ByteArrayDeleteModification((macSize - 1), 1), new ByteArrayExplicitValueModification(padding)));
        return vectorList;
    }