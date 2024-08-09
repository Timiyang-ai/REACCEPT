@SuppressWarnings("unchecked")
  public static <T> ScenarioPerturbation<T> none() {
    // It is safe to cast this to any type because it returns it input with no changes
    return (ScenarioPerturbation<T>) NoOpScenarioPerturbation.INSTANCE;
  }