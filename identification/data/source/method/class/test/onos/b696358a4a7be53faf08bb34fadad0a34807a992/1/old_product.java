@Override
    public boolean setupPath(DeviceId src, DeviceId dst, String tunnelName, List<Constraint> constraints,
                             LspType lspType, List<ExplicitPathInfo> explicitPathInfo) {
        checkNotNull(src);
        checkNotNull(dst);
        checkNotNull(tunnelName);
        checkNotNull(lspType);

        // Convert from DeviceId to TunnelEndPoint
        Device srcDevice = deviceService.getDevice(src);
        Device dstDevice = deviceService.getDevice(dst);

        if (srcDevice == null || dstDevice == null) {
            // Device is not known.
            pceStore.addFailedPathInfo(new PcePathInfo(src, dst, tunnelName, constraints, lspType, explicitPathInfo));
            return false;
        }

        // In future projections instead of annotations will be used to fetch LSR ID.
        String srcLsrId = srcDevice.annotations().value(LSRID);
        String dstLsrId = dstDevice.annotations().value(LSRID);

        if (srcLsrId == null || dstLsrId == null) {
            // LSR id is not known.
            pceStore.addFailedPathInfo(new PcePathInfo(src, dst, tunnelName, constraints, lspType, explicitPathInfo));
            return false;
        }

        // Get device config from netconfig, to ascertain that session with ingress is present.
        DeviceCapability cfg = netCfgService.getConfig(DeviceId.deviceId(srcLsrId), DeviceCapability.class);
        if (cfg == null) {
            log.debug("No session to ingress.");
            pceStore.addFailedPathInfo(new PcePathInfo(src, dst, tunnelName, constraints, lspType, explicitPathInfo));
            return false;
        }

        TunnelEndPoint srcEndPoint = IpTunnelEndPoint.ipTunnelPoint(IpAddress.valueOf(srcLsrId));
        TunnelEndPoint dstEndPoint = IpTunnelEndPoint.ipTunnelPoint(IpAddress.valueOf(dstLsrId));

        double bwConstraintValue = 0;
        CostConstraint costConstraint = null;
        if (constraints != null) {
            constraints.add(CapabilityConstraint.of(CapabilityType.valueOf(lspType.name())));
            Iterator<Constraint> iterator = constraints.iterator();

            while (iterator.hasNext()) {
                Constraint constraint = iterator.next();
                if (constraint instanceof BandwidthConstraint) {
                    bwConstraintValue = ((BandwidthConstraint) constraint).bandwidth().bps();
                } else if (constraint instanceof CostConstraint) {
                    costConstraint = (CostConstraint) constraint;
                }
            }

            /*
             * Add cost at the end of the list of constraints. The path computation algorithm also computes cumulative
             * cost. The function which checks the limiting/capability constraints also returns per link cost. This
             * function can either return the result of limiting/capability constraint validation or the value of link
             * cost, depending upon what is the last constraint in the loop.
             */
            if (costConstraint != null) {
                constraints.remove(costConstraint);
                constraints.add(costConstraint);
            }
        } else {
            constraints = new LinkedList<>();
            constraints.add(CapabilityConstraint.of(CapabilityType.valueOf(lspType.name())));
        }
        Set<Path> computedPathSet = Sets.newLinkedHashSet();

        if (explicitPathInfo != null && !explicitPathInfo.isEmpty()) {
            List<Path> finalComputedPath = computeExplicitPath(explicitPathInfo, src, dst, constraints);
            if (finalComputedPath == null) {
                return false;
            }

            pceStore.tunnelNameExplicitPathInfoMap(tunnelName, explicitPathInfo);
            List<Link> links = new LinkedList<>();
            double totalCost = 0;
            // Add all partial computed paths
            for (Path path : finalComputedPath) {
                links.addAll(path.links());
                totalCost = totalCost + path.cost();
            }
            computedPathSet.add(new DefaultPath(finalComputedPath.iterator().next().providerId(), links, totalCost));
        } else {
            computedPathSet = computePath(src, dst, constraints);
        }

        // NO-PATH
        if (computedPathSet.isEmpty()) {
            pceStore.addFailedPathInfo(new PcePathInfo(src, dst, tunnelName, constraints, lspType, explicitPathInfo));
            return false;
        }

        Builder annotationBuilder = DefaultAnnotations.builder();
        if (bwConstraintValue != 0) {
            annotationBuilder.set(BANDWIDTH, String.valueOf(bwConstraintValue));
        }
        if (costConstraint != null) {
            annotationBuilder.set(COST_TYPE, String.valueOf(costConstraint.type()));
        }
        annotationBuilder.set(LSP_SIG_TYPE, lspType.name());
        annotationBuilder.set(PCE_INIT, TRUE);
        annotationBuilder.set(DELEGATE, TRUE);

        Path computedPath = computedPathSet.iterator().next();

        if (lspType != WITH_SIGNALLING) {
            /*
             * Local LSP id which is assigned by RSVP for RSVP signalled LSPs, will be assigned by
             * PCE for non-RSVP signalled LSPs.
             */
            annotationBuilder.set(LOCAL_LSP_ID, String.valueOf(getNextLocalLspId()));
        }

        // For SR-TE tunnels, call SR manager for label stack and put it inside tunnel.
        Tunnel tunnel = new DefaultTunnel(null, srcEndPoint, dstEndPoint, MPLS, INIT, null, null,
                                          TunnelName.tunnelName(tunnelName), computedPath,
                                          annotationBuilder.build());

        // Allocate bandwidth.
        TunnelConsumerId consumerId = null;
        if (bwConstraintValue != 0) {
            consumerId = reserveBandwidth(computedPath, bwConstraintValue, null);
            if (consumerId == null) {
                pceStore.addFailedPathInfo(new PcePathInfo(src, dst, tunnelName, constraints,
                        lspType, explicitPathInfo));
                return false;
            }
        }

        TunnelId tunnelId = tunnelService.setupTunnel(appId, src, tunnel, computedPath);
        if (tunnelId == null) {
            pceStore.addFailedPathInfo(new PcePathInfo(src, dst, tunnelName, constraints, lspType, explicitPathInfo));
            if (consumerId != null) {
                resourceService.release(consumerId);
            }
            return false;
        }

        if (consumerId != null) {
            // Store tunnel consumer id in LSP store.
            pceStore.addTunnelInfo(tunnelId, consumerId);
        }
        return true;
    }