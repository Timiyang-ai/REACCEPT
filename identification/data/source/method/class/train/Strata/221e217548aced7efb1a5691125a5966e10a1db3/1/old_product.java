public static Index findIndex(String reference) {
    if (IborIndex.extendedEnum().lookupAll().containsKey(reference)) {
      return IborIndex.of(reference);

    } else if (OvernightIndex.extendedEnum().lookupAll().containsKey(reference)) {
      return OvernightIndex.of(reference);

    } else if (PriceIndex.extendedEnum().lookupAll().containsKey(reference)) {
      return PriceIndex.of(reference);

    } else if (FxIndex.extendedEnum().lookupAll().containsKey(reference)) {
      return FxIndex.of(reference);

    } else {
      throw new IllegalArgumentException(Messages.format("No index found for reference: {}", reference));
    }
  }