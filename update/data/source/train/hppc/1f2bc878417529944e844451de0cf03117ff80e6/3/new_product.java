@Override
  public KTypeOpenHashSet<KType> clone() {
    try {
      /* #if ($TemplateOptions.KTypeGeneric) */
      @SuppressWarnings("unchecked")
      /* #end */
      KTypeOpenHashSet<KType> cloned = (KTypeOpenHashSet<KType>) super.clone();
      cloned.keys = keys.clone();
      cloned.allocated = allocated.clone();
      cloned.orderMixer = orderMixer.clone();
      return cloned;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }