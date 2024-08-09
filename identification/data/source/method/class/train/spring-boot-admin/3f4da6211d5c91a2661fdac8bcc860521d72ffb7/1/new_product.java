public Mono<ApplicationId> deregister(ApplicationId id) {
        return repository.computeIfPresent(id, (key, application) -> Mono.just(application.deregister()))
                         .then(Mono.just(id));
    }