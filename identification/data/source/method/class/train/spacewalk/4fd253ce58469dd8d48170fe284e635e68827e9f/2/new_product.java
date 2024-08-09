public static List<Long> listErrataChannelPackages(Long channelId,
            Set<Long> errataIds) {
        SelectMode m = ModeFactory.getMode("Errata_queries",
                "packageids_associated_to_errata");
        Map params = new HashMap();
        params.put("channel_id", channelId);
        DataResult<ErrataPackageFile> dr = m.execute(params, new ArrayList(errataIds));
        // make the final list unique
        Set toReturn = new HashSet<Long>();
        for (ErrataPackageFile file : dr) {
            toReturn.add(file.getPackageId());
        }
        return new ArrayList(toReturn);
    }