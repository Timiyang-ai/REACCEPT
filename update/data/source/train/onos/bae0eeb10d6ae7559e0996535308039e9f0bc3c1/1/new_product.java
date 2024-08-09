@Override
    public boolean setupPath(DeviceId src, DeviceId dst, String tunnelName, List<Constraint> constraints,
                             LspType lspType) {
        return setupPath(src, dst, tunnelName, constraints, lspType, null);
    }