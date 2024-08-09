@VisibleForTesting
    String getEvaluatedCaseValue(WorkflowTask taskToSchedule, Map<String, Object> taskInput) {
        String expression = taskToSchedule.getCaseExpression();
        String caseValue;
        if (expression != null) {
            logger.debug("Case being evaluated using decision expression: {}", expression);
            try {
                //Evaluate the expression by using the Nashhorn based script evaluator
                Object returnValue = ScriptEvaluator.eval(expression, taskInput);
                caseValue = (returnValue == null) ? "null" : returnValue.toString();
            } catch (ScriptException e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException("Error while evaluating the script " + expression, e);
            }

        } else {//In case of no case expression, get the caseValueParam and treat it as a string representation of caseValue
            logger.debug("No Expression available on the decision task, case value being assigned as param name");
            String paramName = taskToSchedule.getCaseValueParam();
            caseValue = "" + taskInput.get(paramName);
        }
        return caseValue;
    }