public BigInteger getMinNonDustValue(BigInteger feePerKbRequired) {
        // Note we skip the *3 as that should be considered in the parameter and we add one to account for loss of precision
        // (1/1000 chance we require too much fee, 999/1000 chance we get the exact right value...)
        return feePerKbRequired.multiply(BigInteger.valueOf(this.bitcoinSerialize().length + 148)).divide(BigInteger.valueOf(1000)).add(BigInteger.ONE);
    }