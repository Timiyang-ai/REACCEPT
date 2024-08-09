@TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getInstance",
          methodArgs = {String.class, String.class}
        )
    })
    public void GetInstance02(int mode) throws NoSuchAlgorithmException,
            NoSuchProviderException, IllegalArgumentException,
            InvalidAlgorithmParameterException {
        try {
            KeyPairGenerator.getInstance(null, mProv.getName());
            fail("NullPointerException or KeyStoreException must be thrown");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyPairGenerator.getInstance(invalidValues[i], mProv.getName());
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        String prov = null;
        for (int i = 0; i < validValues.length; i++) {
            String alg = validValues[i].concat(post);
            try {
                KeyPairGenerator.getInstance(alg, prov);
                fail("IllegalArgumentException must be thrown when provider is null (algorithm: "
                        .concat(alg).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        for (int i = 0; i < validValues.length; i++) {
            String alg = validValues[i].concat(post);
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    KeyPairGenerator.getInstance(alg, invalidValues[j]);
                    fail("NoSuchProviderException must be thrown (algorithm: "
                            .concat(alg).concat(" provider: ").concat(
                                    invalidValues[j]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
        KeyPairGenerator kpG;
        for (int i = 0; i < validValues.length; i++) {
            String alg = validValues[i].concat(post);
            kpG = KeyPairGenerator.getInstance(alg, mProv.getName());
            assertEquals("Incorrect algorithm", kpG.getAlgorithm()
                    .toUpperCase(), (mode <= 2 ? resAlg : alg).toUpperCase());
            assertEquals("Incorrect provider", kpG.getProvider().getName(),
                    mProv.getName());
            checkResult(kpG, mode);
        }
    }