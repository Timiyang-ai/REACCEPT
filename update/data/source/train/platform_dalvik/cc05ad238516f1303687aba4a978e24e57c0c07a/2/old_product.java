@TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getInstance",
          methodArgs = {String.class, Provider.class}
        )
    })
    private void GetInstance03(int mode) throws NoSuchAlgorithmException,
            IllegalArgumentException, InvalidAlgorithmParameterException {
        try {
            KeyPairGenerator.getInstance(null, mProv);
            fail("NullPointerException or KeyStoreException must be thrown");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyPairGenerator.getInstance(invalidValues[i], mProv);
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        Provider prov = null;
        for (int i = 0; i < validValues.length; i++) {
            String alg = validValues[i].concat(post);
            try {
                KeyPairGenerator.getInstance(alg, prov);
                fail("IllegalArgumentException must be thrown when provider is null (algorithm: "
                        .concat(alg).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        KeyPairGenerator kpG;
        for (int i = 0; i < validValues.length; i++) {
            String alg = validValues[i].concat(post);
            kpG = KeyPairGenerator.getInstance(alg, mProv);
            assertEquals("Incorrect algorithm", kpG.getAlgorithm()
                    .toUpperCase(), (mode <= 2 ? resAlg : alg).toUpperCase());
            assertEquals("Incorrect provider", kpG.getProvider(), mProv);
            checkResult(kpG, mode);
        }
    }