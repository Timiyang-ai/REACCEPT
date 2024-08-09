public static <T> double loocv(ClassifierTrainer<T> trainer, T[] x, int[] y, ClassificationMeasure measure) {
        int n = x.length;
        int[] predictions = new int[n];
        
        LOOCV loocv = new LOOCV(n);
        for (int i = 0; i < n; i++) {
            T[] trainx = MathEx.slice(x, loocv.train[i]);
            int[] trainy = MathEx.slice(y, loocv.train[i]);
            
            Classifier<T> classifier = trainer.train(trainx, trainy);

            predictions[loocv.test[i]] = classifier.predict(x[loocv.test[i]]);
        }
        
        return measure.measure(y, predictions);
    }