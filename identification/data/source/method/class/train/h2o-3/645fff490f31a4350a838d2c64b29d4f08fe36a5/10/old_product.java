public static TwoDimTable createScoringHistoryTable(ScoringInfo[] scoringInfo, Model.Parameters params, Model.Output output) {
    boolean hasEpochs = (scoringInfo instanceof HasEpochs[]);
    boolean hasSamples = (scoringInfo instanceof HasSamples[]);
    boolean hasIterations = (scoringInfo instanceof HasIterations[]);

    List<String> colHeaders = new ArrayList<>();
    List<String> colTypes = new ArrayList<>();
    List<String> colFormat = new ArrayList<>();
    colHeaders.add("Timestamp"); colTypes.add("string"); colFormat.add("%s");
    colHeaders.add("Duration"); colTypes.add("string"); colFormat.add("%s");
    if (hasSamples) { colHeaders.add("Training Speed"); colTypes.add("string"); colFormat.add("%s"); }
    if (hasEpochs) { colHeaders.add("Epochs"); colTypes.add("double"); colFormat.add("%.5f"); }
    if (hasIterations) { colHeaders.add("Iterations"); colTypes.add("int"); colFormat.add("%d"); }
    if (hasSamples) { colHeaders.add("Samples"); colTypes.add("double"); colFormat.add("%f"); }
    colHeaders.add("Training MSE"); colTypes.add("double"); colFormat.add("%.5f");

    if (output.getModelCategory() == ModelCategory.Regression) {
      colHeaders.add("Training Deviance"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (!output.isAutoencoder()) {
      colHeaders.add("Training R^2"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (output.isClassifier()) {
      colHeaders.add("Training LogLoss"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (output.getModelCategory() == ModelCategory.Binomial) {
      colHeaders.add("Training AUC"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (output.getModelCategory() == ModelCategory.Binomial) {
      colHeaders.add("Training Lift"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (output.getModelCategory() == ModelCategory.Binomial || output.getModelCategory() == ModelCategory.Multinomial) {
      colHeaders.add("Training Classification Error"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (params._valid != null) {
      colHeaders.add("Validation MSE"); colTypes.add("double"); colFormat.add("%.5f");
      if (output.getModelCategory() == ModelCategory.Regression) {
        colHeaders.add("Validation Deviance"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (!output.isAutoencoder()) {
        colHeaders.add("Validation R^2"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (output.isClassifier()) {
        colHeaders.add("Validation LogLoss"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (output.getModelCategory() == ModelCategory.Binomial) {
        colHeaders.add("Validation AUC"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (output.getModelCategory() == ModelCategory.Binomial) {
        colHeaders.add("Validation Lift"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (output.isClassifier()) {
        colHeaders.add("Validation Classification Error"); colTypes.add("double"); colFormat.add("%.5f");
      }
    } // (params._valid != null)


    final int rows = scoringInfo.length;
    String[] s = new String[0];
    TwoDimTable table = new TwoDimTable(
      "Scoring History", null,
      new String[rows],
      colHeaders.toArray(s),
      colTypes.toArray(s),
      colFormat.toArray(s),
      "");
    int row = 0;
    for (ScoringInfo si : scoringInfo) {
      int col = 0;
      assert (row < table.getRowDim());
      assert (col < table.getColDim());
      DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
      table.set(row, col++, fmt.print(si.time_stamp_ms));
      table.set(row, col++, PrettyPrint.msecs(si.total_training_time_ms, true));

      if (hasSamples) {
//      Log.info("1st speed: (samples: " + si.training_samples + ", total_run_time: " + si.total_training_time_ms + ", total_scoring_time: " + si.total_scoring_time_ms + ", total_setup_time: " + si.total_setup_time_ms + ")");
        int speed = (int) (((HasSamples)si).training_samples() / ((si.total_training_time_ms - si.total_scoring_time_ms - si.total_setup_time_ms) / 1e3));
        assert (speed >= 0) : "Speed should not be negative! " + speed + " = (int)(" + ((HasSamples)si).training_samples() + "/((" + si.total_training_time_ms + "-" + si.total_scoring_time_ms + "-" + si.total_setup_time_ms + ")/1e3)";
        table.set(row, col++, si.total_training_time_ms == 0 ? null : (String.format("%d", speed) + " rows/sec"));
      }
      if (hasEpochs) table.set(row, col++, ((HasEpochs)si).epoch_counter());
      if (hasIterations) table.set(row, col++, ((HasIterations)si).iterations());
      if (hasSamples) table.set(row, col++, ((HasSamples)si).training_samples());

      table.set(row, col++, si.scored_train != null ? si.scored_train._mse : Double.NaN);
      if (output.getModelCategory() == ModelCategory.Regression) {
        table.set(row, col++, si.scored_train != null ? si.scored_train._mean_residual_deviance : Double.NaN);
      }
      if (!output.isAutoencoder()) {
        table.set(row, col++, si.scored_train != null ? si.scored_train._r2 : Double.NaN);
      }
      if (output.isClassifier()) {
        table.set(row, col++, si.scored_train != null ? si.scored_train._logloss : Double.NaN);
      }
      if (output.getModelCategory() == ModelCategory.Binomial) {
        table.set(row, col++, si.training_AUC != null ? si.training_AUC._auc : Double.NaN);
        table.set(row, col++, si.scored_train != null ? si.scored_train._lift : Double.NaN);
      }
      if (output.isClassifier()) {
        table.set(row, col++, si.scored_train != null ? si.scored_train._classError : Double.NaN);
      }
      if (params._valid != null) {
        table.set(row, col++, si.scored_valid != null ? si.scored_valid._mse : Double.NaN);
        if (output.getModelCategory() == ModelCategory.Regression) {
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._mean_residual_deviance : Double.NaN);
        }
        if (!output.isAutoencoder()) {
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._r2 : Double.NaN);
        }
        if (output.isClassifier()) {
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._logloss : Double.NaN);
        }
        if (output.getModelCategory() == ModelCategory.Binomial) {
          table.set(row, col++, si.validation_AUC != null ? si.validation_AUC._auc : Double.NaN);
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._lift : Double.NaN);
        }
        if (output.isClassifier()) {
          table.set(row, col, si.scored_valid != null ? si.scored_valid._classError : Double.NaN);
        }
      }
      row++;
    }
    return table;
  }