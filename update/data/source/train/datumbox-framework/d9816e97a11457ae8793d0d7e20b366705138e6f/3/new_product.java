public static LPResult solve(double[] linearObjectiveFunction, List<LPSolver.LPConstraint> linearConstraintsList, double[] lowBoundsOfVariables, double[] upBoundsOfVariables, boolean[] strictlyIntegerVariables, Integer scalingMode) {
        try {
            return _solve(linearObjectiveFunction, linearConstraintsList, lowBoundsOfVariables, upBoundsOfVariables, strictlyIntegerVariables, scalingMode);
        } 
        catch (LpSolveException ex) {
            throw new RuntimeException(ex);
        }
    }