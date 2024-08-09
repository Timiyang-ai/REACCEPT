public BigInteger getMinNonDustValue(BigInteger feePerKbRequired) {
        // Note we skip the *3 as that should be considered in the parameter
        BigInteger[] nonDustAndRemainder = feePerKbRequired.multiply(BigInteger.valueOf(this.bitcoinSerialize().length + 148)).divideAndRemainder(BigInteger.valueOf(1000));
        return nonDustAndRemainder[1].equals(BigInteger.ZERO) ? nonDustAndRemainder[0] : nonDustAndRemainder[0].add(BigInteger.ONE);
    }