public void detach() {
      // Put the panel's element back where it was.
      if (origParent != null) {
        origParent.insertBefore(element, origSibling);
      } else {
        orphan(element);
      }
    }