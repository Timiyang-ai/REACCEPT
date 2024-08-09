@Override
    public void logCurrentState() {
        StringBuilder logString = new StringBuilder();
        logString.append("Logging current state. Time = ").append(currentTimeMillis()).append("\n");
        logString.append("isStandaloneServer = ").append(isStandaloneServer).append("\n");
        logString.append("maxAllowedLockTimeout = ").append(maxAllowedLockTimeout).append("\n");
        logString.append("maxAllowedClockDrift = ").append(maxAllowedClockDrift).append("\n");
        logString.append("maxAllowedBlockingDuration = ").append(maxAllowedBlockingDuration).append("\n");
        logString.append("randomBitCount = ").append(randomBitCount).append("\n");
        for (Pair<String, ? extends Collection<?>> nameValuePair : ImmutableList.of(
                Pair.create("descriptorToLockMap", descriptorToLockMap.asMap().entrySet()),
                Pair.create("outstandingLockRequestMultimap", outstandingLockRequestMultimap.asMap().entrySet()),
                Pair.create("heldLocksTokenMap", heldLocksTokenMap.entrySet()),
                Pair.create("heldLocksGrantMap", heldLocksGrantMap.entrySet()),
                Pair.create("lockTokenReaperQueue", queueToOrderedList(lockTokenReaperQueue)),
                Pair.create("lockGrantReaperQueue", queueToOrderedList(lockGrantReaperQueue)),
                Pair.create("lockClientMultimap", lockClientMultimap.asMap().entrySet()),
                Pair.create("versionIdMap", versionIdMap.asMap().entrySet()))) {
            Collection<?> elements = nameValuePair.getRhSide();
            logString.append(nameValuePair.getLhSide()).append(".size() = ").append(elements.size()).append("\n");
            if (elements.size() > MAX_LOCKS_TO_LOG) {
                logString.append("WARNING: Only logging the first ").append(MAX_LOCKS_TO_LOG).append(" locks, ");
                logString.append("logging more is likely to OOM or slow down lock server to the point of failure");
            }
            for (Object element : Iterables.limit(elements, MAX_LOCKS_TO_LOG)) {
                logString.append(element).append("\n");
            }
        }
        logString.append("Finished logging current state. Time = ").append(currentTimeMillis());
        log.error("Current State: {}", logString.toString());
    }