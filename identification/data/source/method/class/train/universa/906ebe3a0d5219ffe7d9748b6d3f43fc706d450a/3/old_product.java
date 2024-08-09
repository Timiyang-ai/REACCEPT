@Override
    public byte[] sign(InputStream input, HashType hashType, @Nullable byte[] salt) throws IllegalStateException, IOException {

        if (state == null) {
            throw new IllegalStateException();
        } else {
            Digest primaryDigest = hashType.makeDigest();

            PSSSigner signer;
            if (salt == null) {
                /* Use maximum possible salt */
                signer = new PSSSigner(
                        new RSAEngine(),
                        primaryDigest, state.mgf1HashType.makeDigest(),
                        getMaxSaltLength(getBitStrength(), primaryDigest.getDigestSize()));
            } else {
                /* Use some specific salt */
                signer = new PSSSigner(
                        new RSAEngine(),
                        primaryDigest, state.mgf1HashType.makeDigest(),
                        salt);
            }

            signer.init(true, new ParametersWithRandom(state.keyParameters, state.rng));

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
                        signer.update(buffer, 0, howManyBytesRead);
                    }
                }
            }

            try {
                return signer.generateSignature();
            } catch (CryptoException e) {
                throw new IOException(String.format("Cannot sign data: %s", e.toString()));
            }
        }
    }