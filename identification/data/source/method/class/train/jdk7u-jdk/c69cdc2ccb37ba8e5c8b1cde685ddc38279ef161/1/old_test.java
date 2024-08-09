    public static void divideAndRemainder() {
        int failCount1 = 0;

        for (int i=0; i<size; i++) {
            BigInteger x = fetchNumber(order1).abs();
            while(x.compareTo(BigInteger.valueOf(3L)) != 1)
                x = fetchNumber(order1).abs();
            BigInteger z = x.divide(BigInteger.valueOf(2L));
            BigInteger y[] = x.divideAndRemainder(x);
            if (!y[0].equals(BigInteger.ONE)) {
                failCount1++;
                System.err.println("fail1 x :"+x);
                System.err.println("      y :"+y);
            }
            else if (!y[1].equals(BigInteger.ZERO)) {
                failCount1++;
                System.err.println("fail2 x :"+x);
                System.err.println("      y :"+y);
            }

            y = x.divideAndRemainder(z);
            if (!y[0].equals(BigInteger.valueOf(2))) {
                failCount1++;
                System.err.println("fail3 x :"+x);
                System.err.println("      y :"+y);
            }
        }
        report("divideAndRemainder I", failCount1);
    }