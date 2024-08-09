    @Test
    public void modifyIterationTimeouts() {
        NetworkStretcher ns = NetworkStretcher.builder().build();

        ns.modifyIterationTimeouts();
        assertThat(ns.getCurrentPeriod()).isEqualTo(Duration.ofSeconds(3));

        ns.modifyIterationTimeouts();
        assertThat(ns.getCurrentPeriod()).isEqualTo(Duration.ofSeconds(4));

        ns.modifyIterationTimeouts();
        assertThat(ns.getCurrentPeriod()).isEqualTo(Duration.ofSeconds(5));

        ns.modifyIterationTimeouts();
        assertThat(ns.getCurrentPeriod()).isEqualTo(Duration.ofSeconds(5));

        ns.modifyDecreasedPeriod();
        assertThat(ns.getCurrentPeriod()).isEqualTo(Duration.ofSeconds(4));

        ns.modifyDecreasedPeriod();
        assertThat(ns.getCurrentPeriod()).isEqualTo(Duration.ofSeconds(3));

        ns.modifyDecreasedPeriod();
        assertThat(ns.getCurrentPeriod()).isEqualTo(Duration.ofSeconds(2));

        ns.modifyDecreasedPeriod();
        assertThat(ns.getCurrentPeriod()).isEqualTo(Duration.ofSeconds(2));

        ns.modifyIterationTimeouts();
        assertThat(ns.getCurrentPeriod()).isEqualTo(Duration.ofSeconds(3));
    }