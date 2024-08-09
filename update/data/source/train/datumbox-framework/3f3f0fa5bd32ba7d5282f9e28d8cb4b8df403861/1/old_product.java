public AssociativeArray estimateEfficiency(Map<Object, DeaRecord> id2DeaRecordMapDatabase, Map<Object, DeaRecord> id2DeaRecordMapEvaluation) {
        AssociativeArray evaluatedResults = new AssociativeArray();
        
        List<LPSolver.LPConstraint> constraints = new ArrayList<>();
        
        //initialize the constraints list
        Integer totalColumns = null;
        boolean hasInput = false;
        for(Map.Entry<Object, DeaRecord> entry : id2DeaRecordMapDatabase.entrySet()) {
            DeaRecord currentRecord = entry.getValue();
            int currentColumns = currentRecord.getInput().length; //add the size of input array
            boolean currentHasInput=(currentColumns > 0); //check if the input is defined
            
            currentColumns+=currentRecord.getOutput().length; //add the size of output array
            
            
            if(totalColumns==null) { //if totalColumns is not set, then set them
                //the totalColumns is the sum of input and output columns
                totalColumns=currentColumns;
                hasInput=currentHasInput;
            }
            else { //if totalColumns is initialized, validate that the record has exactly this amount of columns
                if(totalColumns!=currentColumns) {
                    throw new IllegalArgumentException("The input and output columns do not match in all records.");
                }
                if(hasInput!=currentHasInput) {
                    throw new IllegalArgumentException("The input should be used in all records or in none.");
                }
            }
            
            //We have two cases. Either an input is defined for the records or not.
            //The mathematical model is formulated differently depending the case
            if(hasInput==false) {
                //if no input then change the way that the linear problem formulates
                constraints.add(new LPSolver.LPConstraint(currentRecord.getOutput(), "<=", 1.0)); //less than 1
            }
            else {
                //create a double[] with size both of the input and output
                double[] currentConstraintBody = new double[totalColumns];
                
                //set the values of output first on the new array
                double[] conOutput=currentRecord.getOutput();
                for(int i=0;i<conOutput.length;++i) {
                    currentConstraintBody[i]=conOutput[i];
                }
                
                //now set the input by negatiting the values
                double[] conInput=currentRecord.getInput();
                for(int i=0;i<conInput.length;++i) {
                    currentConstraintBody[conOutput.length+i]=-conInput[i];
                }
                //conOutput=null;
                //conInput=null;
                
                //add the constrain on the list
                constraints.add(new LPSolver.LPConstraint(currentConstraintBody, "<=", 0.0)); //less than 0
            }    
        }
        
        
        for(Map.Entry<Object, DeaRecord> entry : id2DeaRecordMapEvaluation.entrySet()) {
            Object currentRecordId = entry.getKey();
            DeaRecord currentRecord = entry.getValue();
            
            double[] objectiveFunction;
            if(hasInput==false) {
                //set the Objection function equal to the output of the record
                objectiveFunction=currentRecord.getOutput();
            }
            else {
                //create a double[] with size both of the input and output
                objectiveFunction = new double[totalColumns];
                double[] denominatorConstraintBody = new double[totalColumns];
                
                //set the values of output first on the new array
                double[] conOutput=currentRecord.getOutput();
                for(int i=0;i<conOutput.length;++i) { 
                    objectiveFunction[i]=conOutput[i]; //set the output to the objective function
                    denominatorConstraintBody[i]=0.0; //set zero to the constraint
                }
                
                //set the values of input first on the new array
                double[] conInput=currentRecord.getInput();
                for(int i=0;i<conInput.length;++i) {
                    objectiveFunction[conOutput.length+i]=0.0; //set zeros on objective function for input
                    denominatorConstraintBody[conOutput.length+i]=conInput[i]; //set the input to the constraint
                }
                //conInput=null;
                //conOutput=null;
                
                //set the denominator equal to 1
                constraints.add(new LPSolver.LPConstraint(denominatorConstraintBody, "=", 1.0));
            }
            
            //RUN SOLVE
            LPSolver.LPResult result = LPSolver.solve(objectiveFunction, constraints, true, true);
            Double objectiveValue = result.getObjectiveValue();
            
            if(hasInput) {
                constraints.remove(constraints.size()-1); //remove the last constraint that you put it
            }
            
            evaluatedResults.put(currentRecordId, objectiveValue);
            
        }
        
        
        return evaluatedResults;
    }