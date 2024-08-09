@Override
  public void assign(String variable, String expression, Map<String, Object> message) {
    Object result = execute(expression, message);
    state.put(variable, result);
  }