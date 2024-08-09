public static boolean checkConsistency(Map<UUID, GridCacheVersion> oldKeyVer,
        Map<UUID, GridCacheVersion> actualKeyVer) {
        assert oldKeyVer.size() > 1;

        if (actualKeyVer.isEmpty())
            return true;

        //TODO Possible you can check it use only one iteration.
        Set<UUID> maxVersions = new HashSet<>();

        maxVersions.add(oldKeyVer.keySet().iterator().next());

        for (Map.Entry<UUID, GridCacheVersion> entry : oldKeyVer.entrySet()) {
            GridCacheVersion lastMaxVer = oldKeyVer.get(maxVersions.iterator().next());
            GridCacheVersion curVer = entry.getValue();

            if (curVer.isGreater(lastMaxVer)) {
                maxVersions.clear();
                maxVersions.add(entry.getKey());
            }
            else if (curVer.equals(lastMaxVer))
                maxVersions.add(entry.getKey());
        }

        GridCacheVersion maxVer = oldKeyVer.get(maxVersions.iterator().next());

        for (UUID maxVerOwner : maxVersions) {
            GridCacheVersion ver = actualKeyVer.get(maxVerOwner);
            if (ver == null || maxVer.isLess(ver))
                return true;
        }

        boolean allNonMaxChanged = true;

        for (Map.Entry<UUID, GridCacheVersion> entry : oldKeyVer.entrySet()) {
            GridCacheVersion actualVer = actualKeyVer.get(entry.getKey());
            if (!maxVersions.contains(entry.getKey()) && (actualVer == null || !actualVer.isGreaterEqual(maxVer))) {
                allNonMaxChanged = false;

                break;
            }
        }

        return allNonMaxChanged;
    }