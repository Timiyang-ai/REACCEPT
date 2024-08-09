@Test
    public void setupPathTest12() {
        build4RouterTopo(false, true, true, true, 15);
        List<Constraint> constraints = new LinkedList<Constraint>();
        BandwidthConstraint bwConstraint = new BandwidthConstraint(Bandwidth.bps(10.0));
        CostConstraint costConstraint = new CostConstraint(TE_COST);

        constraints.add(costConstraint);
        constraints.add(bwConstraint);

        boolean result = pceManager.setupPath(D1.deviceId(), D2.deviceId(), "T123", constraints, SR_WITHOUT_SIGNALLING);
        assertThat(result, is(true));
    }