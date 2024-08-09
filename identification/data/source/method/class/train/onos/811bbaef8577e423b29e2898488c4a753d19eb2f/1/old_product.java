private static void verifyPolicy(ConnectPoint cP1,
                              ConnectPoint cP2,
                              VlanId ingressInner,
                              VlanId ingressOuter,
                              VlanId egressInner,
                              VlanId egressOuter,
                              Long tunnelId) {

        if (cP1.deviceId().equals(cP2.deviceId())) {
            throw new IllegalArgumentException(String.format("Pseudowire connection points can not reside in the " +
                                                                     "same node, in pseudowire %d.", tunnelId));
        }

        // We can have multiple tags, all of them can be NONE,
        // indicating untagged traffic, however, the outer tag can
        // not have value if the inner tag is None
        if (ingressInner.equals(VlanId.NONE) && !ingressOuter.equals(VlanId.NONE)) {
            throw new IllegalArgumentException(String.format("Inner tag should not be empty when " +
                                                                     "outer tag is set for pseudowire %d for cP1.",
                                                             tunnelId));
        }

        if (egressInner.equals(VlanId.NONE) && !egressOuter.equals(VlanId.NONE)) {
            throw new IllegalArgumentException(String.valueOf(String.format("Inner tag should not be empty when" +
                                                                                    " outer tag is set for " +
                                                                                    "pseudowire %d " +
                                                                                    "for cP2.", tunnelId)));
        }

        if (ingressInner.equals(VlanId.ANY) ||
                ingressOuter.equals(VlanId.ANY) ||
                egressInner.equals(VlanId.ANY) ||
                egressOuter.equals(VlanId.ANY)) {
            throw new IllegalArgumentException(String.valueOf(String.format("Wildcard VLAN matching not yet " +
                                                                                    "supported for pseudowire %d.",
                                                                            tunnelId)));
        }

        if (((!ingressOuter.equals(VlanId.NONE) && !ingressInner.equals(VlanId.NONE)) &&
                (egressOuter.equals(VlanId.NONE) && egressInner.equals(VlanId.NONE)))
                || ((ingressOuter.equals(VlanId.NONE) && ingressInner.equals(VlanId.NONE)) &&
                (!egressOuter.equals(VlanId.NONE) && !egressInner.equals(VlanId.NONE)))) {
            throw new IllegalArgumentException(String.valueOf(String.format("Support for double tag <-> untag is not" +
                                                                                    "supported for pseudowire %d.",
                                                                            tunnelId)));
        }
        if ((!ingressInner.equals(VlanId.NONE) &&
                ingressOuter.equals(VlanId.NONE) &&
                !egressOuter.equals(VlanId.NONE))
                || (egressOuter.equals(VlanId.NONE) &&
                !egressInner.equals(VlanId.NONE) &&
                !ingressOuter.equals(VlanId.NONE))) {
            throw new IllegalArgumentException(String.valueOf(String.format("Support for double-tag<->" +
                                                                                    "single-tag is not supported" +
                                                                                    " for pseudowire %d.", tunnelId)));
        }

        if ((ingressInner.equals(VlanId.NONE) && !egressInner.equals(VlanId.NONE))
                || (!ingressInner.equals(VlanId.NONE) && egressInner.equals(VlanId.NONE))) {
            throw new IllegalArgumentException(String.valueOf(String.format("single-tag <-> untag is not supported" +
                                                                                    " for pseudowire %d.", tunnelId)));
        }


        if (!ingressInner.equals(egressInner) && !ingressOuter.equals(egressOuter)) {
            throw new IllegalArgumentException(String.valueOf(String.format("We do not support changing both tags " +
                                                                                    "in double tagged pws, only the " +
                                                                                    "outer," +
                                                                                    " for pseudowire %d.", tunnelId)));
        }

        // check if cp1 and port of cp1 exist
        if (deviceService.getDevice(cP1.deviceId()) == null) {
            throw new IllegalArgumentException(String.valueOf(String.format("cP1 device %s does not exist for" +
                                                                                    " pseudowire %d.", cP1.deviceId(),
                                                                            tunnelId)));
        }

        if (deviceService.getPort(cP1) == null) {
            throw new IllegalArgumentException(String.valueOf(String.format("Port %s for cP1 device %s does not" +
                                                                                    " exist for pseudowire %d.",
                                                                            cP1.port(),
                                                                            cP1.deviceId(), tunnelId)));
        }

        // check if cp2 and port of cp2 exist
        if (deviceService.getDevice(cP2.deviceId()) == null) {
            throw new IllegalArgumentException(String.valueOf(String.format("cP2 device %s does not exist for" +
                                                                                    " pseudowire %d.", cP2.deviceId(),
                                                                            tunnelId)));
        }

        if (deviceService.getPort(cP2) == null) {
            throw new IllegalArgumentException(String.valueOf(String.format("Port %s for cP2 device %s does " +
                                                                                    "not exist for pseudowire %d.",
                                                                            cP2.port(), cP2.deviceId(), tunnelId)));
        }
    }