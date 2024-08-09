@TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getInstance",
          methodArgs = {java.lang.String.class, java.security.Provider.class}
        )
    })
    public void _testGetInstance03() throws NoSuchAlgorithmException,
            IllegalArgumentException,
            InvalidAlgorithmParameterException, CertPathBuilderException {
        try {
            CertPathBuilder.getInstance(null, mProv);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathBuilder.getInstance(invalidValues[i], mProv);
                fail("NoSuchAlgorithmException must be thrown (type: ".concat(
                        invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        Provider prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertPathBuilder.getInstance(validValues[i], prov);
                fail("IllegalArgumentException must be thrown when provider is null (type: "
                        .concat(validValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        CertPathBuilder cerPB;
        for (int i = 0; i < validValues.length; i++) {
            cerPB = CertPathBuilder.getInstance(validValues[i], mProv);
            assertEquals("Incorrect type", cerPB.getAlgorithm(), validValues[i]);
            assertEquals("Incorrect provider", cerPB.getProvider(), mProv);
            checkResult(cerPB);
        }
    }