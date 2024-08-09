  private void submit(TestPrimitive primitive, int count, int total) {
    if (count < total) {
      primitive.write("Hello world!").whenComplete((result, error) -> {
        threadAssertNull(error);
        submit(primitive, count + 1, total);
      });
    } else {
      resume();
    }
  }