public void compile(PathCompilerCreateFlow<T> creator,
                        PathIntent intent,
                        List<T> flows,
                        List<DeviceId> devices) {
        // Note: right now recompile is not considered
        // TODO: implement recompile behavior

        List<Link> links = intent.path().links();

        Optional<EncapsulationConstraint> encapConstraint = intent.constraints().stream()
                .filter(constraint -> constraint instanceof EncapsulationConstraint)
                .map(x -> (EncapsulationConstraint) x).findAny();
        //if no encapsulation or is involved only a single switch use the default behaviour
        if (!encapConstraint.isPresent() || links.size() == 1) {
            for (int i = 0; i < links.size() - 1; i++) {
                ConnectPoint ingress = links.get(i).dst();
                ConnectPoint egress = links.get(i + 1).src();
                creator.createFlow(intent.selector(), intent.treatment(),
                                   ingress, egress, intent.priority(),
                                   isLast(links, i), flows, devices);
            }
        }

        encapConstraint.map(EncapsulationConstraint::encapType)
                .map(type -> {
                    switch (type) {
                        case VLAN:
                            manageVlanEncap(creator, flows, devices, intent);
                            // TODO: implement MPLS case here
                        default:
                            // Nothing to do
                    }
                    return 0;
                });
    }