String expandFilter(String filter, LdapUser ldapUser, User user) {

        filter = expandUserFilter(user, filter, transforms);

        for (Entry<String, Set<String>> entry : ldapUser.getAttributes().entrySet()) {
            if (entry.getValue().size() == 1) {
                try {
                    filter = replace(filter, entry.getKey(),
                            entry.getValue().iterator().next(), transforms);
                } catch (PatternSyntaxException ex) {
                    LOGGER.log(Level.WARNING, "The pattern for expanding is not valid", ex);
                }
            }
        }
        
        filter = filter.replaceAll("\\\\%", "%");
        
        return filter;
    }