public List<OutputT> processBundle(Iterable <? extends InputT> inputElements) throws Exception {
    startBundle();
    for (InputT inputElement : inputElements) {
      processElement(inputElement);
    }
    finishBundle();
    return takeOutputElements();
  }