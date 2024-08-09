protected String expandFilter(String filter, LdapUser ldapUser, User user) {
        filter = filter.replaceAll("(?<!\\\\)%username(?<!\\\\)%", user.getUsername());
        filter = filter.replaceAll("(?<!\\\\)%guid(?<!\\\\)%", user.getId());

        for (Entry<String, Set<String>> entry : ldapUser.getAttributes().entrySet()) {
            if (entry.getValue().size() == 1) {
                try {
                    filter = filter.replaceAll(
                            "(?<!\\\\)%" + entry.getKey() + "(?<!\\\\)%",
                            entry.getValue().iterator().next());
                } catch (PatternSyntaxException ex) {
                    LOGGER.log(Level.WARNING, "The pattern for expanding is not valid", ex);
                }
            }
        }
        
        filter = filter.replaceAll("\\\\%", "%");
        
        return filter;
    }