public List<Double> resolveValues(List<SchedulePeriod> periods) {
    int size = periods.size();
    double[] result = new double[size];
    // handle simple case
    if (steps.size() == 0) {
      Arrays.fill(result, initialValue);
    } else {
      // expand ValueStep to array of adjustments matching the periods
      // the steps are not sorted, so use fixed size array to absorb incoming data
      ValueAdjustment[] expandedSteps = new ValueAdjustment[size];
      for (ValueStep step : steps) {
        int index = step.findIndex(periods);
        if (index == 0) {
          throw new IllegalArgumentException("ValueStep is not allowed at the start of the schedule");
        }
        if (expandedSteps[index] != null && !expandedSteps[index].equals(step.getValue())) {
          throw new IllegalArgumentException("Two ValueStep instances resolve to the same schedule period");
        }
        expandedSteps[index] = step.getValue();
      }
      // apply each adjustment
      double value = initialValue;
      for (int i = 0; i < size; i++) {
        if (expandedSteps[i] != null) {
          value = expandedSteps[i].adjust(value);
        }
        result[i] = value;
      }
    }
    // result array is wrapped, not copied, which is OK as scope of result ends here
    return Doubles.asList(result);
  }