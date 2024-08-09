private MultiPointToSinglePointIntent processRouteAdd(
                RouteEntry routeEntry,
                Collection<Ip4Prefix> withdrawPrefixes) {
        log.debug("Processing route add: {}", routeEntry);

        Ip4Prefix prefix = routeEntry.prefix();
        Ip4Address nextHop = null;
        RouteEntry foundRouteEntry =
            bgpRoutes.put(RouteEntry.createBinaryString(prefix),
                          routeEntry);
        if (foundRouteEntry != null) {
            nextHop = foundRouteEntry.nextHop();
        }

        if (nextHop != null && !nextHop.equals(routeEntry.nextHop())) {
            // There was an existing nexthop for this prefix. This update
            // supersedes that, so we need to remove the old flows for this
            // prefix from the switches
            withdrawPrefixes.add(routeEntry.prefix());
        }
        if (nextHop != null && nextHop.equals(routeEntry.nextHop())) {
            return null;
        }

        if (routeEntry.nextHop().equals(LOCAL_NEXT_HOP)) {
            // Route originated by SDN domain
            // We don't handle these at the moment
            log.debug("Own route {} to {}",
                      routeEntry.prefix(), routeEntry.nextHop());
            return null;
        }

        //
        // Find the MAC address of next hop router for this route entry.
        // If the MAC address can not be found in ARP cache, then this prefix
        // will be put in routesWaitingOnArp queue. Otherwise, generate
        // a new route intent.
        //

        // Monitor the IP address for updates of the MAC address
        hostService.startMonitoringIp(routeEntry.nextHop());

        // Check if we know the MAC address of the next hop
        MacAddress nextHopMacAddress = ip2Mac.get(routeEntry.nextHop());
        if (nextHopMacAddress == null) {
            Set<Host> hosts = hostService.getHostsByIp(routeEntry.nextHop());
            if (!hosts.isEmpty()) {
                // TODO how to handle if multiple hosts are returned?
                nextHopMacAddress = hosts.iterator().next().mac();
            }
            if (nextHopMacAddress != null) {
                ip2Mac.put(routeEntry.nextHop(), nextHopMacAddress);
            }
        }
        if (nextHopMacAddress == null) {
            routesWaitingOnArp.put(routeEntry.nextHop(), routeEntry);
            return null;
        }
        return generateRouteIntent(routeEntry.prefix(), routeEntry.nextHop(),
                                   nextHopMacAddress);
    }