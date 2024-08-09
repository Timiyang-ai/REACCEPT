    @Test
    public void setupPathTest1() {
        build4RouterTopo(true, false, false, false, 0); // IGP cost is set here.
        List<Constraint> constraints = new LinkedList<Constraint>();
        CostConstraint costConstraint = new CostConstraint(COST);
        constraints.add(costConstraint);

        boolean result = pceManager.setupPath(D1.deviceId(), D2.deviceId(), "T123", constraints, WITH_SIGNALLING, null);
        assertThat(result, is(true));
    }