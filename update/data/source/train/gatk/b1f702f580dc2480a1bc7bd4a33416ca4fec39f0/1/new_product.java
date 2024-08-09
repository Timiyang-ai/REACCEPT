public static List<SimpleInterval> mergeSpuriousMiddles(final List<SimpleInterval> segments,
                                                            final List<SimpleInterval> targetSegments,
                                                            final Genome genome) {
        Utils.nonNull(targetSegments, "The list of target segments cannot be null.");
        Utils.nonNull(segments, "The list of segments cannot be null.");
        Utils.nonNull(genome, "The genome cannot be null.");

        final Set<SimpleInterval> targetSegmentsSet = new HashSet<>(targetSegments);
        final List<SimpleInterval> mergedSegments = new ArrayList<>(segments);
        int index = 0;
        while (index < mergedSegments.size()) {
            final SimpleInterval segment = mergedSegments.get(index);
            final int numSNPs = genome.getSNPs().targetCount(segment);
            //if current segment is a spurious middle, merge it with an adjacent segment
            if (numSNPs == 0 && !targetSegmentsSet.contains(segment)) {
                final MergeDirection direction =
                        SmallSegments.calculateMergeDirection(mergedSegments, genome, index);
                if (direction == MergeDirection.LEFT) {
                    //current = merge(left, current), remove left, stay on current during next iteration
                    mergedSegments.set(index, mergeSegments(mergedSegments.get(index - 1), segment));
                    mergedSegments.remove(index - 1);
                    index -= 2;
                } else if (direction == MergeDirection.RIGHT) {
                    //current = merge(current, right), remove right, stay on current during next iteration
                    mergedSegments.set(index, mergeSegments(segment, mergedSegments.get(index + 1)));
                    mergedSegments.remove(index + 1);
                    index--;
                }
            }
            index++; //if no merge performed, go to next segment during next iteration
        }
        return mergedSegments;
    }