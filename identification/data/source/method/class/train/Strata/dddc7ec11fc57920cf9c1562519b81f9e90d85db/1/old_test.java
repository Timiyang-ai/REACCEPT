  @Test
  public void test_parseElements_ByteSource_Fn_noFilter() {
    List<XmlElement> expected = ImmutableList.of(
        XmlElement.ofContent("leaf1", ""),
        XmlElement.ofContent("leaf2", ""),
        XmlElement.ofContent("leaf2", ""),
        XmlElement.ofChildren("obj", ImmutableList.of(XmlElement.ofContent("leaf3", ""))));

    ByteSource source = ByteSource.wrap(SAMPLE.getBytes(StandardCharsets.UTF_8));
    XmlElement test = XmlFile.parseElements(source, name -> Integer.MAX_VALUE);
    assertThat(test.getName()).isEqualTo("base");
    assertThat(test.getAttributes()).isEqualTo(ATTR_MAP_EMPTY);
    assertThat(test.getContent()).isEqualTo("");
    assertThat(test.getChildren().size()).isEqualTo(1);
    XmlElement child = test.getChild(0);
    assertThat(child).isEqualTo(XmlElement.ofChildren("test", expected));
  }