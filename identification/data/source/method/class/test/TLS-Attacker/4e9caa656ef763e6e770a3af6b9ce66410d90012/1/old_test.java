@Test
    public void testDeriveSecret() {
        String macAlgorithm = HKDFAlgorithm.TLS_HKDF_SHA256.getMacAlgorithm().getJavaName();
        byte[] prk = ArrayConverter
                .hexStringToByteArray("31168cad69862a80c6f6bfd42897d0fe23c406a12e652a8d3ae4217694f49844");
        byte[] hashValue = ArrayConverter
                .hexStringToByteArray("52c04472bdfe929772c98b91cf425f78f47659be9d4a7d68b9e29d162935e9b9");
        String labelIn = "client handshake traffic secret";

        byte[] result = HKDFunction.deriveSecret(macAlgorithm, prk, labelIn, hashValue);
        byte[] resultCorrect = ArrayConverter
                .hexStringToByteArray("6c6f274b1eae09b8bbd2039b7eb56147201a5e19288a3fd504fa52b1178a6e93");
        assertArrayEquals(result, resultCorrect);
    }