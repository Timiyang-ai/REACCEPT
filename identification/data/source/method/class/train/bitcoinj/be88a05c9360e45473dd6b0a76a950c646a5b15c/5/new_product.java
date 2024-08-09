public BigInteger getMinNonDustValue(BigInteger feePerKbRequired) {
        // A typical output is 33 bytes (pubkey hash + opcodes) and requires an input of 148 bytes to spend so we add
        // that together to find out the total amount of data used to transfer this amount of value. Note that this
        // formula is wrong for anything that's not a pay-to-address output, unfortunately, we must follow the reference
        // clients wrongness in order to ensure we're considered standard. A better formula would either estimate the
        // size of data needed to satisfy all different script types, or just hard code 33 below.
        final BigInteger size = BigInteger.valueOf(this.bitcoinSerialize().length + 148);
        BigInteger[] nonDustAndRemainder = feePerKbRequired.multiply(size).divideAndRemainder(BigInteger.valueOf(1000));
        return nonDustAndRemainder[1].equals(BigInteger.ZERO) ? nonDustAndRemainder[0] : nonDustAndRemainder[0].add(BigInteger.ONE);
    }