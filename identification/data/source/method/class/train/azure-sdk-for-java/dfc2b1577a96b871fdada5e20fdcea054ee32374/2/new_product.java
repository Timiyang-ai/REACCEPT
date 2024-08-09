Flux<String> getPartitionIds() {
        return getProperties().flatMapMany(properties -> Flux.fromIterable(properties.getPartitionIds()));
    }