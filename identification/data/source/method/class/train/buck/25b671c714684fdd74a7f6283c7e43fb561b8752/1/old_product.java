@SuppressWarnings("unchecked")
    public <T extends FilterAndTargets> T create(
        Class<T> clazz, Optional<Pattern> filter, ImmutableList<BuildTarget> targets) {
      try {
        return (T)
            clazz
                .getMethod("of", Optional.class, ImmutableList.class)
                .invoke(null, filter, targets);
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }