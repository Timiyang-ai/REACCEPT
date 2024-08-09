public Classifier createClassifier(final String name, final String language,
      final File csvTrainingData) {
    if (csvTrainingData == null || !csvTrainingData.exists())
      throw new IllegalArgumentException("csvTrainingData cannot be null or not be found");

    final List<TrainingData> trainingData =
        TrainingDataUtils.fromCSV(csvTrainingData, CSVFormat.DEFAULT);
    return createClassifier(name, language, trainingData);
  }