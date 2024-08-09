public BigInteger[] divideAndRemainder(BigInteger val) {
        BigInteger[] result = new BigInteger[2];
        MutableBigInteger q = new MutableBigInteger(),
                          r = new MutableBigInteger(),
                          a = new MutableBigInteger(this.mag),
                          b = new MutableBigInteger(val.mag);
        a.divide(b, q, r);
        result[0] = new BigInteger(q, this.signum * val.signum);
        result[1] = new BigInteger(r, this.signum);
        return result;
    }