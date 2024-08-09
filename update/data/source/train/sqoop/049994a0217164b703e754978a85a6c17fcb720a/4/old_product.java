public MFramework getFramework() {
    if(framework != null) {
      return framework.clone(false);
    }

    retrieveFramework();
    return framework.clone(false);

  }