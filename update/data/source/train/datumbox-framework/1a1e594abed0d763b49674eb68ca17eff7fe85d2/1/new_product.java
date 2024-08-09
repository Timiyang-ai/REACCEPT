@Override
    public VM validate(Dataframe dataset, TrainingParameters trainingParameters) {
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

        //initialize modeler
        AbstractModeler modeler = Trainable.newInstance(trainingParameters, "kfold_" + RandomGenerator.getThreadLocalRandomUnseeded().nextLong(), conf);

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


            Dataframe trainingData = dataset.getSubset(foldTrainingIds);
            modeler.fit(trainingData);
            trainingData.delete();


            Dataframe validationData = dataset.getSubset(foldValidationIds);

            //fetch validation metrics
            modeler.predict(validationData);

            VM entrySample = ValidationMetrics.newInstance(vmClass, validationData);
            validationData.delete();

            //add the validationMetrics in the list
            validationMetricsList.add(entrySample);
        }
        modeler.delete();

        VM avgValidationMetrics = ValidationMetrics.newInstance(vmClass, validationMetricsList);

        return avgValidationMetrics;
    }