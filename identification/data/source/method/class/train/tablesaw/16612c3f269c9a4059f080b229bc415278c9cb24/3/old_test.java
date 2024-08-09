  @Test
  public void show() {
    PieTrace trace = PieTrace.builder(x, y).build();
    Figure figure = new Figure(trace);
    File outputFile = Paths.get("testoutput/output.html").toFile();
    Plot.show(figure, "target", outputFile);
  }