    @Test
    public void onEmptySwitch() {
        assertThat(VavrListX.empty()
                          .onEmptySwitch(() -> LinkedListX.of(1, 2, 3)),
                   equalTo(LinkedListX.of(1, 2, 3)));
    }