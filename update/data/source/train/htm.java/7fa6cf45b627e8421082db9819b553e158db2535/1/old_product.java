@Deprecated
    public Tuple computeActivity(Set<Cell> activeInput, double activePermanenceThreshold,
        int activeSynapseThreshold, double matchingPermananceThreshold, int matchingSynapseThreshold) {
        
        int nextSegmentIdx = getSegmentCount();
        
        // Object[][] = segments and their counts (i.e. { {segment, count}, {segment, count} } )
        Object[][] numActiveSynapsesForSegment = new Object[nextSegmentIdx][2];
        Arrays.stream(numActiveSynapsesForSegment).forEach(arr -> arr[1] = 0);
        Object[][] numMatchingSynapsesForSegment = new Object[nextSegmentIdx][2];
        Arrays.stream(numMatchingSynapsesForSegment).forEach(arr -> arr[1] = 0);
        
        for(Cell cell : activeInput) {
            for(Synapse synapse : cell.getReceptorSynapses(this)) {
                Segment segment = synapse.getSegment();
                double permanence = synapse.getPermanence();
                if(permanence - matchingPermananceThreshold > -EPSILON) {
                    numMatchingSynapsesForSegment[segment.getIndex()][0] = segment;
                    numMatchingSynapsesForSegment[segment.getIndex()][1] = 
                        ((int)numMatchingSynapsesForSegment[segment.getIndex()][1]) + 1;
                    
                    if(permanence - activePermanenceThreshold > -EPSILON) {
                        numActiveSynapsesForSegment[segment.getIndex()][0] = segment;
                        numActiveSynapsesForSegment[segment.getIndex()][1] = 
                            ((int)numActiveSynapsesForSegment[segment.getIndex()][1]) + 1;
                    }
                }
            }
        }
        
        List<DistalDendrite> activeSegments = new ArrayList<>();
        List<DistalDendrite> matchingSegments = new ArrayList<>();
        for(int i = 0;i < nextSegmentIdx;i++) {
            if(((int)numActiveSynapsesForSegment[i][1]) >= activeSynapseThreshold) {
                activeSegments.add(((DistalDendrite)numActiveSynapsesForSegment[i][0]));
            }
        }
        
        for(int i = 0;i < nextSegmentIdx;i++) {
            if(((int)numMatchingSynapsesForSegment[i][1]) >= matchingSynapseThreshold) {
                matchingSegments.add(((DistalDendrite)numMatchingSynapsesForSegment[i][0]));
            }
        }
        
        return new Tuple(
            (Object)activeSegments.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet<DistalDendrite>::new)),
            (Object)matchingSegments.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet<DistalDendrite>::new)));
    }