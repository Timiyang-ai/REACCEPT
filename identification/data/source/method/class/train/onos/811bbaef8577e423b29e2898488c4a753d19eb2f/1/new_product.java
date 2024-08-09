static void verifyPolicy(ConnectPoint cP1,
                              ConnectPoint cP2,
                              VlanId ingressInner,
                              VlanId ingressOuter,
                              VlanId egressInner,
                              VlanId egressOuter,
                              Long tunnelId) {

        if (cP1.deviceId().equals(cP2.deviceId())) {
            throw new IllegalArgumentException(String.format(ERR_SAME_DEV, tunnelId));
        }

        // We can have multiple tags, all of them can be NONE,
        // indicating untagged traffic, however, the outer tag can
        // not have value if the inner tag is None
        if (ingressInner.equals(VlanId.NONE) && !ingressOuter.equals(VlanId.NONE)) {
            throw new IllegalArgumentException(String.format(ERR_EMPTY_INNER_WHEN_OUTER_PRESENT,
                    tunnelId, "cp1"));
        }

        if (egressInner.equals(VlanId.NONE) && !egressOuter.equals(VlanId.NONE)) {
            throw new IllegalArgumentException(String.format(ERR_EMPTY_INNER_WHEN_OUTER_PRESENT,
                    tunnelId, "cp2"));
        }

        if (ingressInner.equals(VlanId.ANY) ||
                ingressOuter.equals(VlanId.ANY) ||
                egressInner.equals(VlanId.ANY) ||
                egressOuter.equals(VlanId.ANY)) {
            throw new IllegalArgumentException(String.format(ERR_WILDCARD_VLAN, tunnelId));
        }

        if (((!ingressOuter.equals(VlanId.NONE) && !ingressInner.equals(VlanId.NONE)) &&
                (egressOuter.equals(VlanId.NONE) && egressInner.equals(VlanId.NONE)))
                || ((ingressOuter.equals(VlanId.NONE) && ingressInner.equals(VlanId.NONE)) &&
                (!egressOuter.equals(VlanId.NONE) && !egressInner.equals(VlanId.NONE)))) {
            throw new IllegalArgumentException(String.format(ERR_DOUBLE_TO_UNTAGGED, tunnelId));
        }
        if ((!ingressInner.equals(VlanId.NONE) &&
                ingressOuter.equals(VlanId.NONE) &&
                !egressOuter.equals(VlanId.NONE))
                || (egressOuter.equals(VlanId.NONE) &&
                !egressInner.equals(VlanId.NONE) &&
                !ingressOuter.equals(VlanId.NONE))) {
            throw new IllegalArgumentException(String.format(ERR_DOUBLE_TO_SINGLE, tunnelId));
        }

        if ((ingressInner.equals(VlanId.NONE) && !egressInner.equals(VlanId.NONE))
                || (!ingressInner.equals(VlanId.NONE) && egressInner.equals(VlanId.NONE))) {
            throw new IllegalArgumentException(String.format(ERR_SINGLE_TO_UNTAGGED, tunnelId));
        }

        // FIXME PW VLAN translation is not supported on Dune
        //       Need to explore doing that in egress table later if there is a requirement
        if (!ingressInner.equals(egressInner) || !ingressOuter.equals(egressOuter)) {
            throw new IllegalArgumentException(String.format(ERR_VLAN_TRANSLATION, tunnelId));
        }

        if (deviceService == null) {
            throw new IllegalStateException(String.format(ERR_SERVICE_UNAVAIL, "DeviceService"));
        }

        // check if cp1 and port of cp1 exist
        if (deviceService.getDevice(cP1.deviceId()) == null) {
            throw new IllegalArgumentException(String.format(ERR_DEV_NOT_FOUND, cP1.deviceId(), tunnelId));
        }

        if (deviceService.getPort(cP1) == null) {
            throw new IllegalArgumentException(String.format(ERR_PORT_NOT_FOUND, cP1.port(),
                    cP1.deviceId(), tunnelId));
        }

        // check if cp2 and port of cp2 exist
        if (deviceService.getDevice(cP2.deviceId()) == null) {
            throw new IllegalArgumentException(String.format(ERR_DEV_NOT_FOUND, cP2.deviceId(), tunnelId));
        }

        if (deviceService.getPort(cP2) == null) {
            throw new IllegalArgumentException(String.format(ERR_PORT_NOT_FOUND, cP2.port(),
                    cP2.deviceId(), tunnelId));
        }
    }