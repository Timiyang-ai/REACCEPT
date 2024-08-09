@TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getInstance",
          methodArgs = {java.lang.String.class}
        )
    })
    public void _testGetInstance01() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, CertPathValidatorException {
        try {
            CertPathValidator.getInstance(null);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertPathValidator.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown (type: ".concat(
                        invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        CertPathValidator cerPV;
        for (int i = 0; i < validValues.length; i++) {
            cerPV = CertPathValidator.getInstance(validValues[i]);
            assertEquals("Incorrect type", cerPV.getAlgorithm(), validValues[i]);
            assertEquals("Incorrect provider", cerPV.getProvider(), mProv);
            checkResult(cerPV);
        }
    }