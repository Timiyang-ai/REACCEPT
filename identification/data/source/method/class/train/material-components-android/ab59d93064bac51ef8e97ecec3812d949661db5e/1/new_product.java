@NonNull
    public Builder setCornerRadius(@Dimension float cornerRadius) {
      return setTopLeftCornerSize(cornerRadius)
          .setTopRightCornerSize(cornerRadius)
          .setBottomRightCornerSize(cornerRadius)
          .setBottomLeftCornerSize(cornerRadius);
    }