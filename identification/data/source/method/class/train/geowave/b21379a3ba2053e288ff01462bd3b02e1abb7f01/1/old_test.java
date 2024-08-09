  @Test
  public void addFeatureLayer() {
    client.addFeatureLayer("some_workspace", "some_datastore", "some_layer", "some_style");
    Mockito.verify(webTarget).path("rest/layers/some_layer.json");
  }