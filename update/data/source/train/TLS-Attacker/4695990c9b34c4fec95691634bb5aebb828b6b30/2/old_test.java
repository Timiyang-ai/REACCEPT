@Test
    public void testExpandLabel() {
        HKDFAlgorithm hkdfAlgorithm = HKDFAlgorithm.TLS_HKDF_SHA256;
        byte[] prk = ArrayConverter
                .hexStringToByteArray("E056D47C7DB9C04BBECE6AC9525163DE72B7D25B6B0899366F8FA741A5C01709");
        byte[] hashValue = ArrayConverter.hexStringToByteArray("");
        String labelIn = HKDFunction.KEY;
        int outLen = 16;

        byte[] result = HKDFunction.expandLabel(hkdfAlgorithm, prk, labelIn, hashValue, outLen);
        byte[] resultCorrect = ArrayConverter.hexStringToByteArray("04C5DA6EC39FC1653E085FA83E51C6AF");
        assertArrayEquals(result, resultCorrect);
    }