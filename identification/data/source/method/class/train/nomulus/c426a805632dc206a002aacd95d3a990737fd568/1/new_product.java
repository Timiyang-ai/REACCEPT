public static ReservedListEntry create(
        String label, ReservationType reservationType, @Nullable String comment) {
      return new ReservedListEntry.Builder()
          .setLabel(label)
          .setReservationType(reservationType)
          .setComment(comment)
          .build();
    }