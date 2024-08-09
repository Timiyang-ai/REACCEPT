public static Hash calculate(SpongeFactory.Mode mode, int[] trits) {
        return calculate(trits, 0, trits.length, SpongeFactory.create(mode));
    }