public Flux<Application> getApplications() {
        return instanceRegistry.getInstances()
            .filter(Instance::isRegistered)
            .groupBy(instance -> instance.getRegistration().getName())
            .flatMap(grouped -> toApplication(grouped.key(), grouped), Integer.MAX_VALUE);
    }