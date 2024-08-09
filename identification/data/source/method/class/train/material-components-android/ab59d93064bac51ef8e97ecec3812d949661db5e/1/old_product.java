@NonNull
    public Builder setCornerRadius(float cornerRadius) {
      return setTopLeftCornerSize(cornerRadius)
          .setTopRightCornerSize(cornerRadius)
          .setBottomRightCornerSize(cornerRadius)
          .setBottomLeftCornerSize(cornerRadius);
    }