public LocalDate adjusted(ReferenceData refData) {
    return adjustment.adjust(unadjusted, refData);
  }