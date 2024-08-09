public static ECPoint computePE(Chooser chooser, ECCurve curve) throws CryptoException {
        MacAlgorithm randomFunction = getMacAlgorithm(chooser.getSelectedCipherSuite());

        BigInteger prime = curve.getField().getCharacteristic();

        byte[] base;
        byte[] salt = chooser.getServerPWDSalt();
        if (salt == null && chooser.getSelectedProtocolVersion() != ProtocolVersion.TLS13) {
            salt = chooser.getConfig().getDefaultServerPWDSalt();
        }
        if (salt == null) {
            Digest digest = TlsUtils.createHash(HashAlgorithm.sha256);
            base = new byte[digest.getDigestSize()];
            byte[] usernamePW = (chooser.getClientPWDUsername() + chooser.getPWDPassword()).getBytes();
            digest.update(usernamePW, 0, usernamePW.length);
            digest.doFinal(base, 0);
        } else {
            base = StaticTicketCrypto.generateHMAC(MacAlgorithm.HMAC_SHA256,
                    (chooser.getClientPWDUsername() + chooser.getPWDPassword()).getBytes(), salt);
        }

        boolean found = false;
        int counter = 0;
        int n = (curve.getFieldSize() + 64) / 8;
        byte[] context;
        if (chooser.getSelectedProtocolVersion().isTLS13()) {
            context = chooser.getClientRandom();
        } else {
            context = ArrayConverter.concatenate(chooser.getClientRandom(), chooser.getServerRandom());
        }

        BigInteger x = null;
        BigInteger y = null;
        byte[] savedSeed = null;

        do {
            counter++;
            byte[] seedInput = ArrayConverter.concatenate(base, ArrayConverter.intToBytes(counter, 1),
                    ArrayConverter.bigIntegerToByteArray(prime));
            byte[] seed = StaticTicketCrypto.generateHMAC(randomFunction, seedInput, new byte[4]);
            byte[] tmp = prf(chooser, seed, context, n);
            // (tmp mod (p - 1)) + 1
            BigInteger tmpX = new BigInteger(1, tmp).mod(prime.subtract(BigInteger.ONE)).add(BigInteger.ONE);
            // y^2 = (x^3 + x*val + b) mod p
            BigInteger tmpY = tmpX.pow(3).add(tmpX.multiply(curve.getA().toBigInteger()))
                    .add(curve.getB().toBigInteger()).mod(prime);
            // y^((p-1)/2) mod p to test if y is a quadratic residue
            BigInteger legendre = tmpY.modPow(prime.subtract(BigInteger.ONE).shiftRight(1), prime);
            boolean isQuadraticResidue = legendre.compareTo(BigInteger.ONE) == 0;
            if (isQuadraticResidue && !found) {
                x = tmpX;
                y = tmpY;
                savedSeed = seed.clone();
                found = true;
                chooser.getContext().getBadSecureRandom().nextBytes(base);
            }
        } while (!found || counter < chooser.getConfig().getDefaultPWDIterations());
        // y = y^((p+1)/4) mod p = sqrt(y)
        y = y.modPow(prime.add(BigInteger.ONE).shiftRight(2), prime);
        ECPoint PE = curve.createPoint(x, y);

        // use the lsb of the saved seed and Y to determine which of the two
        // possible roots should be used
        int lsbSeed = savedSeed[0] & 1;
        int lsbY = y.getLowestSetBit() == 0 ? 1 : 0;
        if (lsbSeed == lsbY) {
            PE = PE.negate();
        }
        return PE;
    }