    @Test
    public void onEmptySwitch() {
        assertThat(VavrQueueX.empty()
                          .onEmptySwitch(() -> PersistentQueueX.of(1, 2, 3)).toList(),
                   equalTo(PersistentQueueX.of(1, 2, 3).toList()));
    }