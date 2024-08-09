public static ReservedListEntry create(
        String label,
        ReservationType reservationType,
        @Nullable String authCode,
        String comment) {
      if (authCode != null) {
        checkArgument(reservationType == RESERVED_FOR_ANCHOR_TENANT,
            "Only anchor tenant reservations should have an auth code configured");
      } else {
        checkArgument(reservationType != RESERVED_FOR_ANCHOR_TENANT,
            "Anchor tenant reservations must have an auth code configured");
      }
      ReservedListEntry entry = new ReservedListEntry();
      entry.label = label;
      entry.reservationType = reservationType;
      entry.authCode = authCode;
      entry.comment = comment;
      return entry;
    }