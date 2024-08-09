@SafeVarargs
  /* #end */
  public static <KType> KTypeOpenHashSet<KType> from(KType... elements) {
    final KTypeOpenHashSet<KType> set = new KTypeOpenHashSet<KType>(elements.length);
    set.addAll(elements);
    return set;
  }