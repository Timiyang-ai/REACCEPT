public ComputeCycle compute(Connections connections, int[] activeColumns, boolean learn) {
        ComputeCycle result = computeFn(connections, connections.getColumnSet(activeColumns), connections.getPredictiveCells(), 
            connections.getActiveSegments(), connections.getActiveCells(), connections.getWinnerCells(), connections.getMatchingSegments(), 
                connections.getMatchingCells(), learn);
        
        connections.setActiveCells(result.activeCells);
        connections.setWinnerCells(result.winnerCells);
        connections.setPredictiveCells(result.predictiveCells);
        connections.setSuccessfullyPredictedColumns(result.successfullyPredictedColumns);
        connections.setActiveSegments(result.activeSegments);
        connections.setLearningSegments(result.learningSegments);
        connections.setMatchingSegments(result.matchingSegments);
        connections.setMatchingCells(result.matchingCells);
        
        return result; 
    }