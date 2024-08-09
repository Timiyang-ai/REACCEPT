  private void detach(Element div) {
    if (div != null) {
      Element parent = div.getParentElement();
      if (parent != null) {
        parent.removeChild(div);
      }
    }
  }