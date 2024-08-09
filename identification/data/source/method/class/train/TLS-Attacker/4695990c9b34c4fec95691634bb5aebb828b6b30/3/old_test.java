@Test
    public void testDeriveSecret() {
        HKDFAlgorithm hkdfAlgorithm = HKDFAlgorithm.TLS_HKDF_SHA256;
        String hashAlgorithm = DigestAlgorithm.SHA256.getJavaName();
        byte[] prk = ArrayConverter
                .hexStringToByteArray("33AD0A1C607EC03B09E6CD9893680CE210ADF300AA1F2660E1B22E10F170F92A");
        byte[] toHash = ArrayConverter.hexStringToByteArray("");
        String labelIn = HKDFunction.DERIVED;

        byte[] result = HKDFunction.deriveSecret(hkdfAlgorithm, hashAlgorithm, prk, labelIn, toHash);
        byte[] resultCorrect = ArrayConverter
                .hexStringToByteArray("6F2615A108C702C5678F54FC9DBAB69716C076189C48250CEBEAC3576C3611BA");
        assertArrayEquals(result, resultCorrect);
    }