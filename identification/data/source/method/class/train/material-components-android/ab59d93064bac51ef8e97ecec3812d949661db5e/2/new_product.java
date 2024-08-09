@NonNull
    public Builder setAllCornerSizes(@Dimension float cornerSize) {
      return setTopLeftCornerSize(cornerSize)
          .setTopRightCornerSize(cornerSize)
          .setBottomRightCornerSize(cornerSize)
          .setBottomLeftCornerSize(cornerSize);
    }