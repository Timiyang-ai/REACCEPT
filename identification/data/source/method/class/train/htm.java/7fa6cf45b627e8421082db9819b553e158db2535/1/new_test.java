@Test
    public void testComputeActivity() {
        Parameters p = Parameters.getTemporalDefaultParameters();
        p.set(KEY.COLUMN_DIMENSIONS, new int[] { 32 });
        p.set(KEY.CELLS_PER_COLUMN, 32);
        
        Connections connections = new Connections();
        p.apply(connections);
        TemporalMemory.init(connections);
        
        // Cell with 1 segment.
        // Segment with:
        // - 1 connected synapse: active
        // - 2 matching synapses
        DistalDendrite segment1a = connections.createSegment(connections.getCell(10));
        connections.createSynapse(segment1a, connections.getCell(150), .85);
        connections.createSynapse(segment1a, connections.getCell(151), .15);
        
        // Cell with 2 segments.
        // Segment with:
        // - 1 connected synapse: active
        // - 2 matching synapses
        DistalDendrite segment2a = connections.createSegment(connections.getCell(20));
        connections.createSynapse(segment2a, connections.getCell(80), .85);
        connections.createSynapse(segment2a, connections.getCell(81), .85);
        Synapse synapse = connections.createSynapse(segment2a, connections.getCell(82), .85);
        synapse.setPermanence(null, 0.15);
        
        // Segment with:
        // - 2 connected synapses: 1 active, 1 inactive
        // - 3 matching synapses: 2 active, 1 inactive
        // - 1 non-matching synapse: 1 active
        DistalDendrite segment2b = connections.createSegment(connections.getCell(20));
        connections.createSynapse(segment2b, connections.getCell(50), .85);
        connections.createSynapse(segment2b, connections.getCell(51), .85);
        connections.createSynapse(segment2b, connections.getCell(52), .15);
        connections.createSynapse(segment2b, connections.getCell(53), .05);
        
        // Cell with 1 segment.
        // Segment with:
        // - 1 non-matching synapse: 1 active
        DistalDendrite segment3a = connections.createSegment(connections.getCell(30));
        connections.createSynapse(segment3a, connections.getCell(53), .05);
        
        Connections c = connections;
        List<Cell> inputVec = IntStream.of(50, 52, 53, 80, 81, 82, 150, 151)
            .mapToObj(i -> c.getCell(i))
            .collect(Collectors.toList());
        
        Activity activity = c.computeActivity(inputVec, .5, 2, .1, 1, true);
        List<SegmentOverlap> active = activity.activeSegments;
        List<SegmentOverlap> matching = activity.matchingSegments;
        
        assertEquals(1, active.size());
        assertEquals(segment2a, active.get(0).segment);
        assertEquals(2, active.get(0).overlap);
        
        assertEquals(3, matching.size());
        assertEquals(segment1a, matching.get(0).segment);
        assertEquals(2, matching.get(0).overlap);
        assertEquals(segment2a, matching.get(1).segment);
        assertEquals(3, matching.get(1).overlap);
        assertEquals(segment2b, matching.get(2).segment);
        assertEquals(2, matching.get(2).overlap);
    }