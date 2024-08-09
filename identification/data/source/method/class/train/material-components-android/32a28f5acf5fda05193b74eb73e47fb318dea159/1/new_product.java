public void setIcon(@Nullable Drawable icon) {
    if (this.icon != icon) {
      this.icon = icon;
      updateIcon();
    }
  }