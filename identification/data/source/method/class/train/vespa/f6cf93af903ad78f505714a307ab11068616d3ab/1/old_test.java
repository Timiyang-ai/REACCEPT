    private boolean badNode(int numDown, int numUp, int numNotChecked) {
        List<ServiceInstance> services = new ArrayList<>();
        addServiceInstances(services, ServiceStatus.DOWN, numDown);
        addServiceInstances(services, ServiceStatus.UP, numUp);
        addServiceInstances(services, ServiceStatus.NOT_CHECKED, numNotChecked);
        Collections.shuffle(services);

        return NodeFailer.badNode(services);
    }