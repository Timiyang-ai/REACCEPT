public Mono<InstanceId> register(Registration registration) {
        Assert.notNull(registration, "'registration' must not be null");
        InstanceId id = generator.generateId(registration);
        Assert.notNull(id, "'id' must not be null");
        return repository.compute(id, (key, instance) -> {
            if (instance == null) {
                instance = Instance.create(key);
            }
            return Mono.just(instance.register(registration));
        }).then(Mono.just(id));
    }