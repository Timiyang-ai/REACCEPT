static boolean checkExists(String premiumListName) {
    return jpaTm()
        .transact(
            () ->
                jpaTm()
                        .getEntityManager()
                        .createQuery("SELECT 1 FROM PremiumList WHERE name = :name", Integer.class)
                        .setParameter("name", premiumListName)
                        .setMaxResults(1)
                        .getResultList()
                        .size()
                    > 0);
  }