    @Test
    public void remove() {

        VavrListX.of(1, 2, 3)
               .removeAll((Iterable<Integer>) BagX.of(2, 3))
               .mergeMap(i -> Flux.just(10 + i, 20 + i, 30 + i));

    }