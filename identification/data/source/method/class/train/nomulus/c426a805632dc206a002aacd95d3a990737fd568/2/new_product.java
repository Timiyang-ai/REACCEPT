public static ReservedListEntry create(
        String label,
        ReservationType reservationType,
        @Nullable String allowedNameservers,
        @Nullable String comment) {
      ReservedListEntry.Builder entry =
          new ReservedListEntry.Builder()
              .setLabel(label)
              .setComment(comment)
              .setReservationType(reservationType);
      checkArgument(
          (reservationType == NAMESERVER_RESTRICTED) ^ (allowedNameservers == null),
          "Allowed nameservers must be specified for NAMESERVER_RESTRICTED reservations only");
      if (allowedNameservers != null) {
        entry.setAllowedNameservers(
            ImmutableSet.copyOf(Splitter.on(':').trimResults().split(allowedNameservers)));
      }
      return entry.build();
    }