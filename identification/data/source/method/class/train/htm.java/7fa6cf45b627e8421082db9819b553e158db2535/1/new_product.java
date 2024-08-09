public Activity computeActivity(Collection<Cell> activeInput, double activePermanenceThreshold,
        int activeSynapseThreshold, double matchingPermananceThreshold, int matchingSynapseThreshold,
            boolean recordIteration) {
        
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
        
        if(recordIteration) {
            tmIteration++;
        }
        
        List<SegmentOverlap> activeSegments = new ArrayList<>();
        List<SegmentOverlap> matchingSegments = new ArrayList<>();
        for(int i = 0;i < nextSegmentIdx;i++) {
            if(((int)numActiveSynapsesForSegment[i][1]) >= activeSynapseThreshold) {
                activeSegments.add(new SegmentOverlap(((DistalDendrite)numActiveSynapsesForSegment[i][0]),
                    (int)numActiveSynapsesForSegment[i][1]));
                
                if(recordIteration) {
                    ((DistalDendrite)numActiveSynapsesForSegment[i][0]).setLastUsedIteration(tmIteration);
                }
            }
        }
        
        for(int i = 0;i < nextSegmentIdx;i++) {
            if(((int)numMatchingSynapsesForSegment[i][1]) >= matchingSynapseThreshold) {
                matchingSegments.add(new SegmentOverlap(((DistalDendrite)numMatchingSynapsesForSegment[i][0]),
                    (int)numMatchingSynapsesForSegment[i][1]));
            }
        }
        
        Collections.sort(activeSegments, (as1, as2) -> as1.segment.getIndex() - as2.segment.getIndex());
        Collections.sort(matchingSegments, (ms1, ms2) -> ms1.segment.getIndex() - ms2.segment.getIndex());
        return new Activity(activeSegments, matchingSegments);
    }