public static TwoDimTable createScoringHistoryTable(ScoringInfo[] scoringInfos, boolean hasValidation, boolean hasCrossValidation, ModelCategory modelCategory, boolean isAutoencoder) {
    boolean hasEpochs = (scoringInfos instanceof HasEpochs[]);
    boolean hasSamples = (scoringInfos instanceof HasSamples[]);
    boolean hasIterations = (scoringInfos instanceof HasIterations[]);
    boolean isClassifier = (modelCategory == ModelCategory.Binomial || modelCategory == ModelCategory.Multinomial
            || modelCategory == ModelCategory.Ordinal);

    List<String> colHeaders = new ArrayList<>();
    List<String> colTypes = new ArrayList<>();
    List<String> colFormat = new ArrayList<>();
    colHeaders.add("Timestamp"); colTypes.add("string"); colFormat.add("%s");
    colHeaders.add("Duration"); colTypes.add("string"); colFormat.add("%s");
    if (hasSamples) { colHeaders.add("Training Speed"); colTypes.add("string"); colFormat.add("%s"); }
    if (hasEpochs) { colHeaders.add("Epochs"); colTypes.add("double"); colFormat.add("%.5f"); }
    if (hasIterations) { colHeaders.add("Iterations"); colTypes.add("int"); colFormat.add("%d"); }
    if (hasSamples) { colHeaders.add("Samples"); colTypes.add("double"); colFormat.add("%f"); }
    colHeaders.add("Training RMSE"); colTypes.add("double"); colFormat.add("%.5f");
    if (modelCategory == ModelCategory.Regression) {
      colHeaders.add("Training Deviance"); colTypes.add("double"); colFormat.add("%.5f");
      colHeaders.add("Training MAE"); colTypes.add("double"); colFormat.add("%.5f");
      colHeaders.add("Training r2"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (isClassifier) {
      colHeaders.add("Training LogLoss"); colTypes.add("double"); colFormat.add("%.5f");
      colHeaders.add("Training r2"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (modelCategory == ModelCategory.Binomial) {
      colHeaders.add("Training AUC"); colTypes.add("double"); colFormat.add("%.5f");
      colHeaders.add("Training Lift"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (isClassifier) {
      colHeaders.add("Training Classification Error"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if(modelCategory == ModelCategory.AutoEncoder) {
      colHeaders.add("Training MSE"); colTypes.add("double"); colFormat.add("%.5f");
    }
    if (hasValidation) {
      colHeaders.add("Validation RMSE"); colTypes.add("double"); colFormat.add("%.5f");
      if (modelCategory == ModelCategory.Regression) {
        colHeaders.add("Validation Deviance"); colTypes.add("double"); colFormat.add("%.5f");
        colHeaders.add("Validation MAE"); colTypes.add("double"); colFormat.add("%.5f");
        colHeaders.add("Validation r2"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (isClassifier) {
        colHeaders.add("Validation LogLoss"); colTypes.add("double"); colFormat.add("%.5f");
        colHeaders.add("Validation r2"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (modelCategory == ModelCategory.Binomial) {
        colHeaders.add("Validation AUC"); colTypes.add("double"); colFormat.add("%.5f");
        colHeaders.add("Validation Lift"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (isClassifier) {
        colHeaders.add("Validation Classification Error"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if(modelCategory == ModelCategory.AutoEncoder) {
        colHeaders.add("Validation MSE"); colTypes.add("double"); colFormat.add("%.5f");
      }
    } // (hasValidation)
    if (hasCrossValidation) {
      colHeaders.add("Cross-Validation RMSE"); colTypes.add("double"); colFormat.add("%.5f");
      if (modelCategory == ModelCategory.Regression) {
        colHeaders.add("Cross-Validation Deviance"); colTypes.add("double"); colFormat.add("%.5f");
        colHeaders.add("Cross-Validation MAE"); colTypes.add("double"); colFormat.add("%.5f");
        colHeaders.add("Cross-Validation r2"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (isClassifier) {
        colHeaders.add("Cross-Validation LogLoss"); colTypes.add("double"); colFormat.add("%.5f");
        colHeaders.add("Cross-Validation r2"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (modelCategory == ModelCategory.Binomial) {
        colHeaders.add("Cross-Validation AUC"); colTypes.add("double"); colFormat.add("%.5f");
        colHeaders.add("Cross-Validation Lift"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if (isClassifier) {
        colHeaders.add("Cross-Validation Classification Error"); colTypes.add("double"); colFormat.add("%.5f");
      }
      if(modelCategory == ModelCategory.AutoEncoder) {
        colHeaders.add("Cross-Validation MSE"); colTypes.add("double"); colFormat.add("%.5f");
      }
    } // (hasCrossValidation)


    final int rows = scoringInfos == null ? 0 : scoringInfos.length;
    String[] s = new String[0];
    TwoDimTable table = new TwoDimTable(
      "Scoring History", null,
      new String[rows],
      colHeaders.toArray(s),
      colTypes.toArray(s),
      colFormat.toArray(s),
      "");
    int row = 0;

    if (null == scoringInfos)
      return table;

    for (ScoringInfo si : scoringInfos) {
      int col = 0;
      assert (row < table.getRowDim());
      assert (col < table.getColDim());
      DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
      table.set(row, col++, fmt.print(si.time_stamp_ms));
      table.set(row, col++, PrettyPrint.msecs(si.total_training_time_ms, true));

      if (hasSamples) {
//      Log.info("1st speed: (samples: " + si.training_samples + ", total_run_time: " + si.total_training_time_ms + ", total_scoring_time: " + si.total_scoring_time_ms + ", total_setup_time: " + si.total_setup_time_ms + ")");
        float speed = (float) (((HasSamples)si).training_samples() / ((1.+si.total_training_time_ms - si.total_scoring_time_ms - si.total_setup_time_ms) / 1e3));
        assert (speed >= 0) : "Speed should not be negative! " + speed + " = (float)(" + ((HasSamples)si).training_samples() + "/((" + si.total_training_time_ms + "-" + si.total_scoring_time_ms + "-" + si.total_setup_time_ms + ")/1e3)";
        table.set(row, col++, si.total_training_time_ms == 0 ? null : (
                speed>10 ? String.format("%d", (int)speed) : String.format("%g", speed)
        ) + " obs/sec");
      }
      if (hasEpochs) table.set(row, col++, ((HasEpochs)si).epoch_counter());
      if (hasIterations) table.set(row, col++, ((HasIterations)si).iterations());
      if (hasSamples) table.set(row, col++, ((HasSamples)si).training_samples());

      table.set(row, col++, si.scored_train != null ? si.scored_train._rmse : Double.NaN);
      if (modelCategory == ModelCategory.Regression) {
        table.set(row, col++, si.scored_train != null ? si.scored_train._mean_residual_deviance : Double.NaN);
        table.set(row, col++, si.scored_train != null ? si.scored_train._mae : Double.NaN);
        table.set(row, col++, si.scored_train != null ? si.scored_train._r2 : Double.NaN);
      }
      if (isClassifier) {
        table.set(row, col++, si.scored_train != null ? si.scored_train._logloss : Double.NaN);
        table.set(row, col++, si.scored_train != null ? si.scored_train._r2 : Double.NaN);
      }
      if (modelCategory == ModelCategory.Binomial) {
        table.set(row, col++, si.training_AUC != null ? si.training_AUC._auc : Double.NaN);
        table.set(row, col++, si.scored_train != null ? si.scored_train._lift : Double.NaN);
      }
      if (isClassifier) {
        table.set(row, col++, si.scored_train != null ? si.scored_train._classError : Double.NaN);
      }
      if (isAutoencoder) {
        table.set(row, col++, si.scored_train != null ? si.scored_train._mse : Double.NaN);
      }
      if (hasValidation) {
        table.set(row, col++, si.scored_valid != null ? si.scored_valid._rmse : Double.NaN);
        if (modelCategory == ModelCategory.Regression) {
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._mean_residual_deviance : Double.NaN);
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._mae : Double.NaN);
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._r2 : Double.NaN);
        }
        if (isClassifier) {
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._logloss : Double.NaN);
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._r2 : Double.NaN);
        }
        if (modelCategory == ModelCategory.Binomial) {
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._AUC : Double.NaN);
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._lift : Double.NaN);
        }
        if (isClassifier) {
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._classError : Double.NaN);
        }
        if (isAutoencoder) {
          table.set(row, col++, si.scored_valid != null ? si.scored_valid._mse : Double.NaN);
        }
      } // hasValidation
      if (hasCrossValidation) {
        table.set(row, col++, si.scored_xval != null ? si.scored_xval._rmse : Double.NaN);
        if (modelCategory == ModelCategory.Regression) {
          table.set(row, col++, si.scored_xval != null ? si.scored_xval._mean_residual_deviance : Double.NaN);
          table.set(row, col++, si.scored_xval != null ? si.scored_xval._mae : Double.NaN);
          table.set(row, col++, si.scored_xval != null ? si.scored_xval._r2 : Double.NaN);
        }
        if (isClassifier) {
          table.set(row, col++, si.scored_xval != null ? si.scored_xval._logloss : Double.NaN);
          table.set(row, col++, si.scored_xval != null ? si.scored_xval._r2 : Double.NaN);
        }
        if (modelCategory == ModelCategory.Binomial) {
          table.set(row, col++, si.scored_xval != null ? si.scored_xval._AUC : Double.NaN);
          table.set(row, col++, si.scored_xval != null ? si.scored_xval._lift : Double.NaN);
        }
        if (isClassifier) {
          table.set(row, col, si.scored_xval != null ? si.scored_xval._classError : Double.NaN);
        }
        if (isAutoencoder) {
          table.set(row, col++, si.scored_xval != null ? si.scored_xval._mse : Double.NaN);
        }
      } // hasCrossValidation
      row++;
    }
    return table;
  }