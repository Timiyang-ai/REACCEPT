public static ReservedListEntry create(
        String label,
        ReservationType reservationType,
        @Nullable String restrictions,
        @Nullable String comment) {
      ReservedListEntry.Builder builder =
          new ReservedListEntry.Builder()
              .setLabel(label)
              .setComment(comment)
              .setReservationType(reservationType);
      if (restrictions != null) {
        checkArgument(
            reservationType == RESERVED_FOR_ANCHOR_TENANT
                || reservationType == NAMESERVER_RESTRICTED,
            "Only anchor tenant and nameserver restricted reservations "
                + "should have restrictions imposed");
        if (reservationType == RESERVED_FOR_ANCHOR_TENANT) {
          builder.setAuthCode(restrictions);
        } else if (reservationType == NAMESERVER_RESTRICTED) {
          builder.setAllowedNameservers(
              ImmutableSet.copyOf(Splitter.on(':').trimResults().split(restrictions)));
        }
      } else {
        checkArgument(
            reservationType != RESERVED_FOR_ANCHOR_TENANT,
            "Anchor tenant reservations must have an auth code configured");
        checkArgument(
            reservationType != NAMESERVER_RESTRICTED,
            "Nameserver restricted reservations must have at least one nameserver configured");
      }
      return builder.build();
    }