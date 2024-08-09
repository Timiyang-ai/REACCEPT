public static void saveNew(PremiumList premiumList) {
    jpaTm()
        .transact(
            () -> {
              checkArgument(
                  !checkExists(premiumList.getName()),
                  "Premium list '%s' already exists",
                  premiumList.getName());
              jpaTm().getEntityManager().persist(premiumList);
            });
  }