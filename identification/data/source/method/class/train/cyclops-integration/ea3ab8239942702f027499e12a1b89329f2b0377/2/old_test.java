    @Test
    public void onEmptySwitch() {
        assertThat((LazyPOrderedSetX) VavrTreeSetX.empty()
                          .onEmptySwitch(() -> (LazyPOrderedSetX) VavrTreeSetX.of(1, 2, 3)),
                   equalTo(OrderedSetX.of(1, 2, 3)));
    }