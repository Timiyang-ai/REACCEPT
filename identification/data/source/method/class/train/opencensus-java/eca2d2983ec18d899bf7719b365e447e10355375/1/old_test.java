  @Test
  public void create() {
    Map<String, String> labelMap = new HashMap<String, String>();
    labelMap.put("a", "1");
    labelMap.put("b", "2");
    Resource resource = Resource.create("t1", labelMap);
    assertThat(resource.getType()).isNotNull();
    assertThat(resource.getType()).isEqualTo("t1");
    assertThat(resource.getLabels()).isNotNull();
    assertThat(resource.getLabels().size()).isEqualTo(2);
    assertThat(resource.getLabels()).isEqualTo(labelMap);

    Resource resource1 = Resource.create(null, Collections.<String, String>emptyMap());
    assertThat(resource1.getType()).isNull();
    assertThat(resource1.getLabels()).isNotNull();
    assertThat(resource1.getLabels()).isEmpty();
  }