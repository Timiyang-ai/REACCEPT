public static void registerProvider(ZoneRulesProvider provider) {
        ZoneRulesProvider old = GROUPS.putIfAbsent(provider.getGroupId(), provider);
        if (old != null) {
            throw new DateTimeException("Provider already registered for time-zone group: " + provider.getGroupId());
        }
    }