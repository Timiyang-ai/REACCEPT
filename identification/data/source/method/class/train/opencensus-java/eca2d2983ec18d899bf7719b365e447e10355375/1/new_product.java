public static Resource createFromEnvironmentVariables() {
    return createInternal(ENV_TYPE, ENV_LABEL_MAP);
  }