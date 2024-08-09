public void unmount(int index, MountItem mountItem) {
    final Object content = mountItem.getBaseContent();
    if (content instanceof Drawable) {
      ensureDrawableMountItems();

      unmountDrawable(mountItem);
      ComponentHostUtils.removeItem(index, mDrawableMountItems, mScrapDrawableMountItems);
    } else if (content instanceof View) {
      unmountView((View) content);

      ensureViewMountItems();
      ComponentHostUtils.removeItem(index, mViewMountItems, mScrapViewMountItemsArray);
      mIsChildDrawingOrderDirty = true;
      maybeUnregisterTouchExpansion(index, mountItem);
    }

    ensureMountItems();
    ComponentHostUtils.removeItem(index, mMountItems, mScrapMountItemsArray);
    releaseScrapDataStructuresIfNeeded();
    maybeInvalidateAccessibilityState(mountItem);
  }