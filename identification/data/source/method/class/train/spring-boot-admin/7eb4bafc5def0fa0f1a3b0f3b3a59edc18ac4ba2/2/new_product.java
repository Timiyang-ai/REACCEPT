public Mono<Application> getApplication(String name) {
        return this.toApplication(name, this.instanceRegistry.getInstances(name).filter(Instance::isRegistered))
            .filter(a -> !a.getInstances().isEmpty());
    }