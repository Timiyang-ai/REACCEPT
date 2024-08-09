public void mount(int index, MountItem mountItem, Rect bounds) {
    final Object content = mountItem.getContent();
    if (content instanceof Drawable) {
      mountDrawable(index, mountItem, bounds);
    } else if (content instanceof View) {
      ensureViewMountItems();
      mViewMountItems.put(index, mountItem);
      mountView((View) content, mountItem.getFlags());
      maybeRegisterTouchExpansion(index, mountItem);
    }

    ensureMountItems();
    mMountItems.put(index, mountItem);

    maybeInvalidateAccessibilityState(mountItem);
  }