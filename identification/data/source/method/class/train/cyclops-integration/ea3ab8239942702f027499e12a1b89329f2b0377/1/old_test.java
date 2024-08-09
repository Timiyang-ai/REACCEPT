    @Test
    public void onEmptySwitch() {
        assertThat(VavrHashSetX.empty()
                          .onEmptySwitch(() -> PersistentSetX.of(1, 2, 3)).toList(),
                   equalTo(VavrHashSetX.of(1, 2, 3).toList()));
    }