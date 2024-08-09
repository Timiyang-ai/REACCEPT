    @Override
    protected Stream<Character> rangeBy(char from, char toExclusive, int step) {
        return Stream.rangeBy(from, toExclusive, step);
    }