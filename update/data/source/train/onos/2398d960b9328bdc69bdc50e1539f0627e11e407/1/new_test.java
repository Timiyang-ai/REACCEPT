@Test
    public void setupPathFailureNotEnoughBandwidthRegistered() {
        build4RouterTopo(false, false, false, false, 5);
        List<Constraint> constraints = new LinkedList<>();
        BandwidthConstraint bwConstraint = new BandwidthConstraint(
                Bandwidth.bps(10.0));
        CostConstraint costConstraint = new CostConstraint(TE_COST);

        constraints.add(costConstraint);
        constraints.add(bwConstraint);

        boolean result = pceManager.setupPath(D1.deviceId(), D2.deviceId(),
                TUNNEL_NAME, constraints, WITH_SIGNALLING, null);
        assertThat(result, is(false));
    }