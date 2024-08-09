public BigInteger[] divideAndRemainder(BigInteger val) {
        BigInteger[] result = new BigInteger[2];
        MutableBigInteger q = new MutableBigInteger(),
                          a = new MutableBigInteger(this.mag),
                          b = new MutableBigInteger(val.mag);
        MutableBigInteger r = a.divide(b, q);
        result[0] = q.toBigInteger(this.signum == val.signum ? 1 : -1);
        result[1] = r.toBigInteger(this.signum);
        return result;
    }