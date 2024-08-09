@Test
    public void testExpandLabel() {
        String macAlgorithm = HKDFAlgorithm.TLS_HKDF_SHA256.getMacAlgorithm().getJavaName();
        byte[] prk = ArrayConverter
                .hexStringToByteArray("b2c2663ed59e833b17c68823516f11f1cb311855045d3ce46bfe8ac8889268d9");
        byte[] hashValue = ArrayConverter.hexStringToByteArray("");
        String labelIn = HKDFunction.IV;
        int outLen = 12;

        byte[] result = HKDFunction.expandLabel(macAlgorithm, prk, labelIn, hashValue, outLen);
        byte[] resultCorrect = ArrayConverter.hexStringToByteArray("a353bfcdf9695a2a09c2e293");
        assertArrayEquals(result, resultCorrect);
    }