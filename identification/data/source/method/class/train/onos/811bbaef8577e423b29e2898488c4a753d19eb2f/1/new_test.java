    @Test
    public void verifyPolicy() {
        cp1 = new ConnectPoint(DID1, PN1);
        cp2 = new ConnectPoint(DID2, PN2);
        ingressInner = V1;
        ingressOuter = V2;
        egressInner = V1;
        egressOuter = V2;
        PwaasUtil.verifyPolicy(cp1, cp2, ingressInner, ingressOuter, egressInner, egressOuter, TUNNEL_ID);

        ingressInner = VlanId.NONE;
        ingressOuter = VlanId.NONE;
        egressInner = VlanId.NONE;
        egressOuter = VlanId.NONE;
        PwaasUtil.verifyPolicy(cp1, cp2, ingressInner, ingressOuter, egressInner, egressOuter, TUNNEL_ID);
    }