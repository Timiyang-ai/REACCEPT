public void setBaselineTopology(Collection<String> ids) {
        authorizeIfNeeded(ctx.security(), SecurityPermission.ADMIN_OPS);

        Set<UUID> uuids = ids.stream().map(UUID::fromString).collect(Collectors.toSet());

        Collection<ClusterNode> nodes = ctx.grid().cluster().forNodeIds(uuids).forServers().nodes();
        ctx.grid().cluster().setBaselineTopology(nodes);
    }