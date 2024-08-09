private void GetInstance01(int mode) throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {
        try {
            KeyPairGenerator.getInstance(null);
            fail("NullPointerException or KeyStoreException must be thrown");
        } catch (NoSuchAlgorithmException e) {
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyPairGenerator.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        KeyPairGenerator kpG;
        for (int i = 0; i < validValues.length; i++) {
            String alg = validValues[i].concat(post);
            kpG = KeyPairGenerator.getInstance(alg);
            assertEquals("Incorrect algorithm", kpG.getAlgorithm()
                    .toUpperCase(), (mode <= 2 ? resAlg : alg).toUpperCase());
            assertEquals("Incorrect provider", kpG.getProvider(), mProv);
            checkResult(kpG, mode);
        }
    }