void processRouteAdd(RouteEntry routeEntry) {
        synchronized (this) {
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
                executeRouteDelete(routeEntry);
            }
            if (nextHop != null && nextHop.equals(routeEntry.nextHop())) {
                return;
            }

            if (routeEntry.nextHop().equals(LOCAL_NEXT_HOP)) {
                // Route originated by SDN domain
                // We don't handle these at the moment
                log.debug("Own route {} to {}",
                          routeEntry.prefix(), routeEntry.nextHop());
                return;
            }

            executeRouteAdd(routeEntry);
        }
    }