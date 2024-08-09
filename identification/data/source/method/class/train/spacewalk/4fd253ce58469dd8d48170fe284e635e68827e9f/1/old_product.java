public static List<Long> listErrataChannelPackages(Long cid, Long eid) {
        Map params = new HashMap();
        params.put("channel_id", cid);
        params.put("errata_id", eid);
        DataResult<ErrataPackageFile> dr = executeSelectMode(
                "ErrataCache_queries",
                "package_associated_to_errata_and_channel", params);
        List toReturn = new ArrayList<Long>();
        for (ErrataPackageFile file : dr) {
            toReturn.add(file.getPackageId());
        }
        return toReturn;
    }