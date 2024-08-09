public static LPResult solve(double[] linearObjectiveFunction, List<LPSolver.LPConstraint> linearConstraintsList, boolean nonNegative, boolean maximize) {
        int m = linearConstraintsList.size();

        List<LinearConstraint> constraints = new ArrayList<>(m);
        for(LPSolver.LPConstraint constraint : linearConstraintsList) {
            String sign = constraint.getSign();
            Relationship relationship = null;
            if(LPSolver.GEQ.equals(sign)) {
                relationship = Relationship.GEQ;
            }
            else if(LPSolver.LEQ.equals(sign)) {
                relationship = Relationship.LEQ;
            }
            else if(LPSolver.EQ.equals(sign)) {
                relationship = Relationship.EQ;
            }
            constraints.add(
                    new LinearConstraint(constraint.getContraintBody(),
                    relationship,
                    constraint.getValue())
            );
        }

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(
                new LinearObjectiveFunction(linearObjectiveFunction, 0.0),
                new LinearConstraintSet(constraints),
                maximize?GoalType.MAXIMIZE:GoalType.MINIMIZE,
                new NonNegativeConstraint(nonNegative),
                PivotSelectionRule.BLAND
        );

        LPResult result = new LPResult();
        result.setObjectiveValue(solution.getValue());
        result.setVariableValues(solution.getPoint());

        return result;
    }