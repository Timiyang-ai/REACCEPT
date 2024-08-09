public Flux<PartitionEvent> receive() {
        return allPartitionsFlux;
    }