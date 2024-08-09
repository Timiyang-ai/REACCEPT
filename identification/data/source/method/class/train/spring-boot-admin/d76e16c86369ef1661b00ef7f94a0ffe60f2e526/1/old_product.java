public Mono<InstanceId> deregister(InstanceId id) {
        return repository.computeIfPresent(id, (key, instance) -> Mono.just(instance.deregister())).then(Mono.just(id));
    }