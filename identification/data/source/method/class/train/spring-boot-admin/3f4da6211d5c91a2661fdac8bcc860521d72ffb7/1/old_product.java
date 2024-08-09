public Mono<ApplicationId> deregister(ApplicationId id) {
        return repository.find(id)
                         .map(Application::deregister)
                         .flatMap(repository::save)
                         .retryWhen(Retry.anyOf(OptimisticLockingException.class)
                                         .fixedBackoff(Duration.ofMillis(50L))
                                         .retryMax(10)
                                         .doOnRetry(ctx -> log.debug("Retrying after OptimisticLockingException",
                                                 ctx.exception())))
                         .then(Mono.just(id));
    }