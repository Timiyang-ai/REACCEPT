@Override
  public void assign(String variable, String expression, JSONObject message) {
    Object result = execute(expression, message);
    state.put(variable, result);
  }