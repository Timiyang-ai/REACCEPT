@Override
    public boolean setupPath(DeviceId src, DeviceId dst, String tunnelName, List<Constraint> constraints,
                             LspType lspType) {
        checkNotNull(src);
        checkNotNull(dst);
        checkNotNull(tunnelName);
        checkNotNull(constraints);
        checkNotNull(lspType);

        // TODO: compute and setup path.
        //TODO: gets the path based on constraints and creates a tunnel in network via tunnel manager
        return computePath(src, dst, constraints) != null ? true : false;
    }