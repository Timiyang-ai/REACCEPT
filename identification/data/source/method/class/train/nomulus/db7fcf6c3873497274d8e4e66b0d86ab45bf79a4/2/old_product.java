public static void saveNew(PremiumList premiumList) {
    jpaTm()
        .transact(
            () -> {
              checkArgument(
                  !checkExists(premiumList.getName()),
                  "A premium list of this name already exists: %s.",
                  premiumList.getName());
              jpaTm().getEntityManager().persist(premiumList);
            });
  }