  @Test
  public void getRegionType() {
    assertThat(converter.getRegionType("ABC", null))
        .isEqualTo(RegionType.LEGACY);

    assertThat(converter.getRegionType(null, null)).isEqualTo(RegionType.LEGACY);

    RegionAttributesType regionAttributes = new RegionAttributesType();
    assertThat(converter.getRegionType(null, regionAttributes)).isEqualTo(RegionType.LEGACY);

    regionAttributes.setDataPolicy(RegionAttributesDataPolicy.PARTITION);
    assertThat(converter.getRegionType(null, regionAttributes)).isEqualTo(RegionType.PARTITION);

    RegionAttributesType.PartitionAttributes pAttributes =
        new RegionAttributesType.PartitionAttributes();
    pAttributes.setLocalMaxMemory("20000");
    regionAttributes.setPartitionAttributes(pAttributes);
    assertThat(converter.getRegionType(null, regionAttributes)).isEqualTo(RegionType.PARTITION);
    assertThat(converter.getRegionType("PARTITION_REDUNDANT", regionAttributes))
        .isEqualTo(RegionType.PARTITION);

    pAttributes.setLocalMaxMemory("0");
    assertThat(converter.getRegionType(null, regionAttributes))
        .isEqualTo(RegionType.PARTITION_PROXY);
    assertThat(converter.getRegionType("PARTITION_PROXY_REDUNDANT", regionAttributes))
        .isEqualTo(RegionType.PARTITION_PROXY);
    assertThat(converter.getRegionType("PARTITION_PROXY", regionAttributes))
        .isEqualTo(RegionType.PARTITION_PROXY);

    regionAttributes.setDataPolicy(RegionAttributesDataPolicy.PERSISTENT_PARTITION);
    assertThat(converter.getRegionType(null, regionAttributes))
        .isEqualTo(RegionType.PARTITION_PERSISTENT);

    regionAttributes.setDataPolicy(RegionAttributesDataPolicy.REPLICATE);
    assertThat(converter.getRegionType(null, regionAttributes)).isEqualTo(RegionType.REPLICATE);

    regionAttributes.setDataPolicy(RegionAttributesDataPolicy.PERSISTENT_REPLICATE);
    assertThat(converter.getRegionType(null, regionAttributes))
        .isEqualTo(RegionType.REPLICATE_PERSISTENT);

    regionAttributes.setDataPolicy(RegionAttributesDataPolicy.EMPTY);
    assertThat(converter.getRegionType(null, regionAttributes))
        .isEqualTo(RegionType.REPLICATE_PROXY);

    regionAttributes.setDataPolicy(RegionAttributesDataPolicy.NORMAL);
    assertThat(converter.getRegionType(null, regionAttributes)).isEqualTo(RegionType.LEGACY);

    regionAttributes.setDataPolicy(RegionAttributesDataPolicy.PRELOADED);
    assertThat(converter.getRegionType(null, regionAttributes)).isEqualTo(RegionType.LEGACY);
  }