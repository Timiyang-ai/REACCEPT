public void mount(int index, MountItem mountItem, Rect bounds) {
    final Object content = mountItem.getBaseContent();
    if (content instanceof Drawable) {
      mountDrawable(index, mountItem, bounds);
    } else if (content instanceof View) {
      ensureViewMountItems();
      mViewMountItems.put(index, mountItem);
      mountView((View) content, mountItem.getLayoutFlags());
      maybeRegisterTouchExpansion(index, mountItem);
    }

    ensureMountItems();
    mMountItems.put(index, mountItem);

    maybeInvalidateAccessibilityState(mountItem);
  }