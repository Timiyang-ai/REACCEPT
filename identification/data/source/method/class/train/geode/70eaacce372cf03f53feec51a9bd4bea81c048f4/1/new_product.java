public RegionType getRegionType(String refid, RegionAttributesType regionAttributes) {
    if (refid != null) {
      try {
        return RegionType.valueOf(refid);
      } catch (Exception e) {
        return RegionType.LEGACY;
      }
    }

    // if refid is null, we will try to determine the type based on the region attributes
    if (regionAttributes == null) {
      return RegionType.LEGACY;
    }
    RegionAttributesDataPolicy dataPolicy = regionAttributes.getDataPolicy();

    if (dataPolicy == null) {
      return RegionType.LEGACY;
    }

    switch (dataPolicy) {
      case PARTITION: {
        RegionAttributesType.PartitionAttributes partitionAttributes =
            regionAttributes.getPartitionAttributes();
        if (partitionAttributes != null && "0".equals(partitionAttributes.getLocalMaxMemory())) {
          return RegionType.PARTITION_PROXY;
        }
        return RegionType.PARTITION;
      }
      case PERSISTENT_PARTITION: {
        return RegionType.PARTITION_PERSISTENT;
      }
      case PERSISTENT_REPLICATE: {
        return RegionType.REPLICATE_PERSISTENT;
      }
      case REPLICATE: {
        return RegionType.REPLICATE;
      }
      case EMPTY: {
        return RegionType.REPLICATE_PROXY;
      }
    }
    return RegionType.LEGACY;
  }