@Override
    public long deleteTerminatedClusters() {
        return this.clusterRepository.deleteByIdIn(
            this.clusterRepository
                .findTerminatedUnusedClusters()
                .stream()
                .map(Number::longValue)
                .collect(Collectors.toSet())
        );
    }