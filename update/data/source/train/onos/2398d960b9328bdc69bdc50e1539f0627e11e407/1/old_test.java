@Test
    public void setupPathTest8() {
        build4RouterTopo(true, false, false, false, 0);
        List<Constraint> constraints = new LinkedList<Constraint>();
        BandwidthConstraint bwConstraint = new BandwidthConstraint(Bandwidth.bps(10.0));
        CostConstraint costConstraint = new CostConstraint(TE_COST);

        constraints.add(costConstraint);
        constraints.add(bwConstraint);

        boolean result = pceManager.setupPath(D1.deviceId(), D2.deviceId(), "T123", constraints,
                WITH_SIGNALLING, null);
        assertThat(result, is(false));
    }