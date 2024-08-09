public void setBaselineTopology(Collection<String> consIds) {
        authorizeIfNeeded(ctx.security(), SecurityPermission.ADMIN_OPS);

        ctx.grid().cluster().setBaselineTopology(baselineNodesForIds(consIds));
    }