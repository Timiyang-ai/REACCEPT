@Override
  public void lazyLoadMany(EntityBean current) {
    EntityBean parentBean = childMasterProperty.getValueAsEntityBean(current);
    if (parentBean != null) {
      addBeanToCollectionWithCreate(parentBean, current, true);
    }
  }