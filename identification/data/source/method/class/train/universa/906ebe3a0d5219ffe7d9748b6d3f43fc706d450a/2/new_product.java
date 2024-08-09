@NonNull
    @Override
    public boolean checkSignature(InputStream input, byte[] signature, HashType hashType, int saltLength) throws
            IllegalStateException, IOException {

        if (state == null) {
            throw new IllegalStateException();
        } else {
            final Digest primaryDigest = hashType.makeDigest();

            if (saltLength == MAX_SALT_LENGTH) {
                saltLength = getMaxSaltLength(getBitStrength(), primaryDigest.getDigestSize());
            }
            if (saltLength < 0) {
                throw new RuntimeException(String.format("Incorrect salt length %s", saltLength));
            }

            final Signer signatureChecker = new PSSSigner(
                    RSAEngineFactory.make(),
                    primaryDigest, state.mgf1HashType.makeDigest(),
                    saltLength);
            signatureChecker.init(false, new ParametersWithRandom(state.keyParameters, state.rng));

            boolean done = false;
            while (!done) {
                int availableBytes = input.available();
                if (availableBytes <= 0) {
                    done = true;
                } else {
                    byte[] buffer = new byte[availableBytes];
                    int howManyBytesRead = input.read(buffer);
                    if (howManyBytesRead <= 0) {
                        done = true;
                    } else {
                        signatureChecker.update(buffer, 0, howManyBytesRead);
                    }
                }
            }

            return signatureChecker.verifySignature(signature);
        }
    }