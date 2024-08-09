@Test
    public void updatePathTest3() {
        build4RouterTopo(false, true, true, true, 100);

        // Setup tunnel.
        List<Constraint> constraints = new LinkedList<Constraint>();
        CostConstraint costConstraint = new CostConstraint(TE_COST);
        constraints.add(costConstraint);
        boolean result = pceManager.setupPath(D1.deviceId(), D2.deviceId(), "T123", constraints, WITH_SIGNALLING);
        assertThat(result, is(true));

        Collection<Tunnel> tunnels = (Collection<Tunnel>) pceManager.queryAllPath();
        assertThat(tunnels.size(), is(1));

        for (Tunnel tunnel : tunnels) {
            result = pceManager.updatePath(tunnel.tunnelId(), null);
            assertThat(result, is(true));
        }

        Iterable<Tunnel> queryTunnelResult = pceManager.queryAllPath();
        assertThat((int) queryTunnelResult.spliterator().getExactSizeIfKnown(), is(2));
    }