    @Test
    public void onEmptySwitch() {
        assertThat(VavrVectorX.empty()
                          .onEmptySwitch(() -> VectorX.of(1, 2, 3)),
                   equalTo(VectorX.of(1, 2, 3)));
    }