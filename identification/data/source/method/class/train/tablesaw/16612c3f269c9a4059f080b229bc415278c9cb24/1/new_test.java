  @Test
  public void show() {

    BarTrace trace = BarTrace.builder(x, y).build();
    Figure figure = new Figure(trace);
    Plot.show(figure, "target");
  }