public static LPResult solve(double[] linearObjectiveFunction, List<LPSolver.LPConstraint> linearConstraintsList, double[] lowBoundsOfVariables, double[] upBoundsOfVariables, boolean[] strictlyIntegerVariables, Integer scalingMode) throws LpSolveException {
        //Important note. All the double[] arrays that are passed to the lpsolve
        //lib MUST start from 1. Do not use the 0 index because it is discarded.
        
        
        int n = linearObjectiveFunction.length; //columns/variables
        int m = linearConstraintsList.size();
        
        LPResult result = new LPResult(n,m);
        
        LpSolve solver = LpSolve.makeLp(0, n);
        solver.setVerbose(LpSolve.NEUTRAL); //set verbose level
        solver.setMaxim(); //set the problem to maximization
        solver.setObjFn(pad1ZeroInfront(linearObjectiveFunction));
        
        if(scalingMode!=null) {
            solver.setScaling(scalingMode);
        }
        
        //lower bounds for the variables
        if(lowBoundsOfVariables!=null && n==lowBoundsOfVariables.length) {
            for(int i=0;i<n;++i) {
                solver.setLowbo(i+1, lowBoundsOfVariables[i]); //plus one is required by the lib
            }
        }
        
        //upper bounds for the variables
        if(upBoundsOfVariables!=null && n==upBoundsOfVariables.length) {
            for(int i=0;i<n;++i) {
                solver.setUpbo(i+1, upBoundsOfVariables[i]); //plus one is required by the lib
            }
        }
        
        //set variables that are strictly integers
        if(strictlyIntegerVariables!=null && n==strictlyIntegerVariables.length) {
            for(int i=0;i<n;++i) {
                solver.setInt(i+1, strictlyIntegerVariables[i]); //plus one is required by the lib
            }
        }
        
        for(LPSolver.LPConstraint constraint : linearConstraintsList) {
            solver.addConstraint(pad1ZeroInfront(constraint.getContraintBody()), constraint.getSign(), constraint.getValue());
        }
        
        
        //solve the problem
        int status = solver.solve();
        if(isSolutionValid(status)==false) {
            solver.setScaling(LpSolve.SCALE_NONE); //turn off automatic scaling
            status = solver.solve();
            if(isSolutionValid(status)==false) {
                throw new RuntimeException("LPSolver: "+solver.getStatustext(status));
                //solver.printLp();
            }
        }
                
        
        result.setObjectiveValue((Double) solver.getObjective());
        solver.getVariables(result.getVariableValues());
        solver.getDualSolution(result.getDualSolution());
        
        //delete problem and free the memory
        solver.deleteLp();
        
        return result;
    }