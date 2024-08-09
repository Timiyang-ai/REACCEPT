public VM kFoldCrossValidation(Dataframe dataset, int k, String dbName, DatabaseConfiguration dbConf, Class<? extends AbstractModeler> aClass, TP trainingParameters) {
        int n = dataset.size();
        if(k<=0 || n<=k) {
            throw new IllegalArgumentException("Invalid number of folds.");
        }
        
        int foldSize= n/k; //floor the number
        
        
        //shuffle the ids of the records
        Integer[] ids = new Integer[n];
        int j =0;
        for(Integer rId : dataset.index()) {
            ids[j]=rId;
            ++j;
        }
        PHPMethods.shuffle(ids);
        
        AbstractModeler<MP, TP, VM> mlmodel;
        
        String foldDBname=dbName+dbConf.getDBnameSeparator()+DB_INDICATOR;
        
        List<VM> validationMetricsList = new LinkedList<>();
        for(int fold=0;fold<k;++fold) {
            
            logger.info("Kfold {}", fold);
            
            //as fold window we consider the part of the ids that are used for validation
            FlatDataList foldTrainingIds = new FlatDataList(new ArrayList<>(n-foldSize));
            FlatDataList foldValidationIds = new FlatDataList(new ArrayList<>(foldSize));
            
            for(int i=0;i<n;++i) {
                boolean isInValidationFoldRange = false;
                
                //determine if the current i value is in the validation fold range
                if(fold*foldSize<=i && i<(fold+1)*foldSize) {
                    isInValidationFoldRange = true;
                }
                
                if(isInValidationFoldRange) {
                    foldValidationIds.add(ids[i]);
                }
                else {
                    foldTrainingIds.add(ids[i]);
                }
            }
            
            if(k==1) {
                //if the number of k folds is 1 then the trainindIds are empty
                //and the all the data are on validation fold. In this case
                //we should set the training and validation sets equal
                foldTrainingIds = foldValidationIds;
            }
            
            
            //initialize mlmodel
            mlmodel = Trainable.newInstance(aClass, foldDBname+(fold+1), dbConf);
            
            
            Dataframe trainingData = dataset.getSubset(foldTrainingIds);
            mlmodel.fit(trainingData, trainingParameters); 
            trainingData.delete();
            //trainingData = null;
                        
            
            Dataframe validationData = dataset.getSubset(foldValidationIds);
            
            //fetch validation metrics
            VM entrySample = mlmodel.validate(validationData);
            validationData.delete();
            //validationData = null;
            
            //delete algorithm
            mlmodel.delete();
            //mlmodel = null;
            
            //add the validationMetrics in the list
            validationMetricsList.add(entrySample);
        }
        
        VM avgValidationMetrics = calculateAverageValidationMetrics(validationMetricsList);
        
        return avgValidationMetrics;
    }