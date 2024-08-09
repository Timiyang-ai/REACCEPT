@SuppressWarnings("unchecked")
    @Override
    public ComputeCycle compute(Connections conn, int[] activeColumns, boolean learn) {
        ComputeCycle cycle = new ComputeCycle();
        
        Set<Cell> prevActiveCells = conn.getActiveCells();
        Set<Cell> prevWinnerCells = conn.getWinnerCells();
        
        Arrays.sort(activeColumns);
        
        for(ExcitedColumn excitedColumn : excitedColumnsGenerator(
            activeColumns, new ArrayList<>(conn.getActiveSegments()), 
                new ArrayList<>(conn.getMatchingSegments()), conn)) {
            
            if(excitedColumn.isActiveColumn) {
                if(excitedColumn.activeSegmentsCount != 0) {
                    List<Cell> cellsToAdd = activatePredictedColumn(
                        conn, excitedColumn, prevActiveCells, conn.getPermanenceIncrement(), 
                            conn.getPermanenceDecrement(), learn);
                    
                    cycle.activeCells.addAll(cellsToAdd);
                    cycle.winnerCells.addAll(cellsToAdd);
                } else {
                    Tuple bestCellxWinnerCell = burstColumn(
                        conn, excitedColumn, prevActiveCells, prevWinnerCells, conn.getMaxNewSynapseCount(),
                            conn.getInitialPermanence(), conn.getPermanenceIncrement(), 
                                conn.getPermanenceDecrement(), conn.getRandom(), learn);
                    
                    cycle.activeCells.addAll((List<Cell>)bestCellxWinnerCell.get(0));
                    cycle.winnerCells.add((Cell)bestCellxWinnerCell.get(1));
                }
            } else if(learn) {
                punishPredictedColumn(conn, excitedColumn, prevActiveCells, conn.getPredictedSegmentDecrement());
            }
        }
        
        // Tuple = [activeSegments, matchingSegments]
        Tuple activeMatchingSegments = conn.computeActivity(cycle.activeCells, conn.getConnectedPermanence(), 
            conn.getActivationThreshold(), 0.0, conn.getMinThreshold());
        
        cycle.activeSegments = (Set<DistalDendrite>)activeMatchingSegments.get(0);
        cycle.matchingSegments = (Set<DistalDendrite>)activeMatchingSegments.get(1);
        
        conn.setActiveCells(new LinkedHashSet<>(cycle.activeCells));
        conn.setWinnerCells(new LinkedHashSet<>(cycle.winnerCells));
        conn.setActiveSegments(new LinkedHashSet<>(cycle.activeSegments));
        conn.setMatchingSegments(new LinkedHashSet<>(cycle.matchingSegments));
        
        return cycle;
    }