public static int countSmallSegments(final List<SimpleInterval> segments,
                                         final TargetCollection <TargetCoverage> targets,
                                         final int targetNumberThreshold) {
        Utils.nonNull(segments, "The list of segments cannot be null.");
        Utils.nonNull(targets, "The collection of targets cannot be null.");
        return (int) segments.stream()
                .filter(s -> targets.targetCount(s) < targetNumberThreshold)
                .count();
    }