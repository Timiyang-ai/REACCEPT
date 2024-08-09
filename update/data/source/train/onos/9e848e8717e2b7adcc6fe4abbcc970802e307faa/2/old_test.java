@Test
    public void setupPathTest12() {
        build4RouterTopo(false, true, true, true, 15);
        List<Constraint> constraints = new LinkedList<Constraint>();
        BandwidthConstraint bwConstraint = new BandwidthConstraint(Bandwidth.bps(10.0));
        CostConstraint costConstraint = new CostConstraint(TE_COST);

        constraints.add(costConstraint);
        constraints.add(bwConstraint);

        LabelResourceId node1Label = LabelResourceId.labelResourceId(5200);
        LabelResourceId node2Label = LabelResourceId.labelResourceId(5201);

        pceManager.pceStore.addGlobalNodeLabel(D1.deviceId(), node1Label);
        pceManager.pceStore.addGlobalNodeLabel(D2.deviceId(), node2Label);

        LabelResourceId link1Label = LabelResourceId.labelResourceId(5202);
        pceManager.pceStore.addAdjLabel(link1, link1Label);

        LabelResourceId link2Label = LabelResourceId.labelResourceId(5203);
        pceManager.pceStore.addAdjLabel(link2, link2Label);

        LabelResourceId link3Label = LabelResourceId.labelResourceId(5204);
        pceManager.pceStore.addAdjLabel(link3, link3Label);

        LabelResourceId link4Label = LabelResourceId.labelResourceId(5205);
        pceManager.pceStore.addAdjLabel(link4, link4Label);

        boolean result = pceManager.setupPath(D1.deviceId(), D2.deviceId(), "T123", constraints, SR_WITHOUT_SIGNALLING);
        assertThat(result, is(true));
    }