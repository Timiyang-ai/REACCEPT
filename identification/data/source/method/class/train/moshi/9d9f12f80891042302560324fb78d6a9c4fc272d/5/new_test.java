  @Test public void lenient() throws Exception {
    JsonAdapter<Double> lenient = new JsonAdapter<Double>() {
      @Override public Double fromJson(JsonReader reader) throws IOException {
        return reader.nextDouble();
      }

      @Override public void toJson(JsonWriter writer, Double value) throws IOException {
        writer.value(value);
      }
    }.lenient();

    JsonReader reader = factory.newReader("[-Infinity, NaN, Infinity]");
    reader.beginArray();
    assertThat(lenient.fromJson(reader)).isEqualTo(Double.NEGATIVE_INFINITY);
    assertThat(lenient.fromJson(reader)).isNaN();
    assertThat(lenient.fromJson(reader)).isEqualTo(Double.POSITIVE_INFINITY);
    reader.endArray();

    JsonWriter writer = factory.newWriter();
    writer.beginArray();
    lenient.toJson(writer, Double.NEGATIVE_INFINITY);
    lenient.toJson(writer, Double.NaN);
    lenient.toJson(writer, Double.POSITIVE_INFINITY);
    writer.endArray();
    assertThat(factory.json()).isEqualTo("[-Infinity,NaN,Infinity]");
  }