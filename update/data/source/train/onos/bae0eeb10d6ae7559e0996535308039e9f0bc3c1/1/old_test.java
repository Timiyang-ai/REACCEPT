@Test
    public void setupPathTest4() {
        build4RouterTopo(true, false, false, false, 0); // IGP cost is set here, not TE.

        List<Constraint> constraints = new LinkedList<Constraint>();
        CostConstraint costConstraint = new CostConstraint(TE_COST);
        constraints.add(costConstraint);

        boolean result = pceManager.setupPath(D1.deviceId(), D2.deviceId(), "T123", constraints, WITH_SIGNALLING);
        assertThat(result, is(false));
    }