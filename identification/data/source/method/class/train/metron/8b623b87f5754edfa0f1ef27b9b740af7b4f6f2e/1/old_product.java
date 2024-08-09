@Override
  public void assign(String variable, String expression, JSONObject message, Context stellarContext) {
    Object result = execute(expression, message, stellarContext);
    state.put(variable, result);
  }