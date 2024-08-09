public static ReservedListEntry create(
        String label,
        ReservationType reservationType,
        @Nullable String restrictions,
        String comment) {
      ReservedListEntry entry = new ReservedListEntry();
      if (restrictions != null) {
        checkArgument(
            reservationType == RESERVED_FOR_ANCHOR_TENANT
                || reservationType == NAMESERVER_RESTRICTED,
            "Only anchor tenant and nameserver restricted reservations "
                + "should have restrictions imposed");
        if (reservationType == RESERVED_FOR_ANCHOR_TENANT) {
          entry.authCode = restrictions;
        } else if (reservationType == NAMESERVER_RESTRICTED) {
          Set<String> allowedNameservers =
              ImmutableSet.copyOf(Splitter.on(':').trimResults().split(restrictions));
          checkNameserversAreValid(allowedNameservers);
          entry.allowedNameservers = Joiner.on(',').join(allowedNameservers);
        }
      } else {
        checkArgument(reservationType != RESERVED_FOR_ANCHOR_TENANT,
            "Anchor tenant reservations must have an auth code configured");
        checkArgument(
            reservationType != NAMESERVER_RESTRICTED,
            "Nameserver restricted reservations must have at least one nameserver configured");
      }
      entry.label = label;
      entry.comment = comment;
      entry.reservationType = reservationType;
      return entry;
    }