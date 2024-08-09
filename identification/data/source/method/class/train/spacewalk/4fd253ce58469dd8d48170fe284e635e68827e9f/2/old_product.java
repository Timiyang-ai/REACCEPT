public static List<Long> listErrataChannelPackages(Set<Long> errataIds) {
        SelectMode m = ModeFactory.getMode("Errata_queries",
                "packageids_associated_to_errata");
        DataResult<ErrataPackageFile> dr = m.execute(new HashMap(),
                new ArrayList(errataIds));
        Set toReturn = new HashSet<Long>();
        for (ErrataPackageFile file : dr) {
            toReturn.add(file.getPackageId());
        }
        return new ArrayList(toReturn);
    }