@NotNull private PreprocessingTrainer makePreprocessorTrainer(String preprocessorClassName) throws Exception {
        return new PreprocessingTrainer() {
            @Override public Preprocessor fit(LearningEnvironmentBuilder envBuilder, DatasetBuilder datasetBuilder,
                Preprocessor basePreprocessor) {
                try {
                    return createPreprocessor(basePreprocessor, preprocessorClassName);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }