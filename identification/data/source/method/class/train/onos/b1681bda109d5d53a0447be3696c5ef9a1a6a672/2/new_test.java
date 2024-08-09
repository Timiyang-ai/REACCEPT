@Test
    public void testCost() {
        sut = new LatencyConstraint(Duration.of(10, ChronoUnit.MICROS));

        assertThat(sut.cost(link1, resourceContext), is(closeTo(Double.parseDouble(LATENCY1), 1.0e-6)));
        assertThat(sut.cost(link2, resourceContext), is(closeTo(Double.parseDouble(LATENCY2), 1.0e-6)));
    }