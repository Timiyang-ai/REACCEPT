    private Set<HostSpec> prepare(ApplicationId application, ClusterSpec... specs) {
        return prepare(application, Capacity.fromCount(2, new NodeResources(1, 4, 10, 0.3), false, true), false, specs);
    }