public static RSAKeyPair fromExponents(byte[] e, byte[] p, byte[] q) {
        final BigInteger
                eInt = BigIntegers.fromUnsignedByteArray(e),
                pInt = BigIntegers.fromUnsignedByteArray(p),
                qInt = BigIntegers.fromUnsignedByteArray(q),
                nInt = pInt.multiply(qInt),
                mInt = pInt.subtract(BigInteger.ONE).multiply(qInt.subtract(BigInteger.ONE)),
                dInt = eInt.modInverse(mInt),
                dPInt = dInt.remainder(pInt.subtract(BigInteger.ONE)),
                dQInt = dInt.remainder(qInt.subtract(BigInteger.ONE)),
                qInvInt = qInt.modInverse(pInt);
        final byte[]
                n = BigIntegers.asUnsignedByteArray(nInt),
                d = BigIntegers.asUnsignedByteArray(dInt),
                dP = BigIntegers.asUnsignedByteArray(dPInt),
                dQ = BigIntegers.asUnsignedByteArray(dQInt),
                qInv = BigIntegers.asUnsignedByteArray(qInvInt);

        return new RSAKeyPair(n, e, d, p, q, dP, dQ, qInv);
    }