@Test
    public void testSolve() {
        logger.info("solve");
        
        //Example from http://lpsolve.sourceforge.net/5.5/PHP.htm
        double[] linearObjectiveFunction = {143.0, 60.0, 195.0};
        List<LPSolver.LPConstraint> linearConstraintsList = new ArrayList<>();
        linearConstraintsList.add(new LPSolver.LPConstraint(new double[]{120.0, 210.0, 150.75}, "<=", 15000.0));
        linearConstraintsList.add(new LPSolver.LPConstraint(new double[]{110.0, 30.0, 125.0}, "<=", 4000.0));
        linearConstraintsList.add(new LPSolver.LPConstraint(new double[]{1.0, 1.0, 1.0}, "<=", 75.0));

        LPSolver.LPResult expResult = new LPSolver.LPResult();
        expResult.setObjectiveValue(6986.8421052632);
        expResult.setVariableValues(new double[]{0.0, 56.578947368421, 18.421052631579});
        
        LPSolver.LPResult result = LPSolver.solve(linearObjectiveFunction, linearConstraintsList, true, true);
        assertEquals(expResult.getObjectiveValue(), result.getObjectiveValue(), Constants.DOUBLE_ACCURACY_HIGH);
        assertArrayEquals(expResult.getVariableValues(), result.getVariableValues(), Constants.DOUBLE_ACCURACY_HIGH);
        
    }