@Override
  public Results calculate(
      CalculationRules calculationRules,
      List<? extends CalculationTarget> targets,
      List<Column> columns,
      MarketData marketData,
      ReferenceData refData) {

    CalculationTasks tasks = CalculationTasks.of(calculationRules, targets, columns);
    return taskRunner.calculate(tasks, marketData, refData);
  }