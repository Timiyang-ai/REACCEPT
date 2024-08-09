@Test
    public void testSolve() {
        logger.info("solve");
        
        //Example from http://lpsolve.sourceforge.net/5.5/PHP.htm
        double[] linearObjectiveFunction = {143.0, 60.0, 195.0};
        List<LPSolver.LPConstraint> linearConstraintsList = new ArrayList<>();
        linearConstraintsList.add(new LPSolver.LPConstraint(new double[]{120.0, 210.0, 150.75}, LpSolve.LE, 15000.0));
        linearConstraintsList.add(new LPSolver.LPConstraint(new double[]{110.0, 30.0, 125.0}, LpSolve.LE, 4000.0));
        linearConstraintsList.add(new LPSolver.LPConstraint(new double[]{1.0, 1.0, 1.0}, LpSolve.LE, 75.0));
        
        /*
        double[] lowBoundsOfVariables = null;
        double[] upBoundsOfVariables = null;
        boolean[] strictlyIntegerVariables = null;
        */
        Integer scalingMode = LpSolve.SCALE_EXTREME;
        
        LPSolver.LPResult expResult = new LPSolver.LPResult(3, 3);
        expResult.setObjectiveValue((Double) 6986.8421052632);
        expResult.setVariableValues(new double[]{0.0, 56.578947368421, 18.421052631579});
        expResult.setDualSolution(new double[]{1.0, 0.0, 1.4210526315789, 17.368421052632, -30.684210526315788, 0.0, 0.0});
        
        LPSolver.LPResult result = LPSolver.solve(linearObjectiveFunction, linearConstraintsList, null, null, null, scalingMode);
        assertEquals(expResult.getObjectiveValue(), result.getObjectiveValue(), Constants.DOUBLE_ACCURACY_HIGH);
        assertArrayEquals(expResult.getVariableValues(), result.getVariableValues(), Constants.DOUBLE_ACCURACY_HIGH);
        assertArrayEquals(expResult.getDualSolution(), result.getDualSolution(), Constants.DOUBLE_ACCURACY_HIGH);
        
    }