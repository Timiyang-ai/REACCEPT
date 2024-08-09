public BigInteger toNanos() {
        return BigInteger.valueOf(seconds).multiply(BI_NANOS_PER_SECOND).add(BigInteger.valueOf(nanos));
    }