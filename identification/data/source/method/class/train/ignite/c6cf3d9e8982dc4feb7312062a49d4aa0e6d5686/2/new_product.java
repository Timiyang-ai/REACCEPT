@NotNull private PreprocessingTrainer makePreprocessorTrainer(String preprocessorClsName) throws Exception {
        return new PreprocessingTrainer() {
            @Override public Preprocessor fit(LearningEnvironmentBuilder envBuilder, DatasetBuilder datasetBuilder,
                Preprocessor basePreprocessor) {
                try {
                    return createPreprocessor(basePreprocessor, preprocessorClsName);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }