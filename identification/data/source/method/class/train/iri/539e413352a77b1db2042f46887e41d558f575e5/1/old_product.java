public static Hash calculate(int[] trits) {
        return calculate(trits, 0, trits.length, new Curl());
    }