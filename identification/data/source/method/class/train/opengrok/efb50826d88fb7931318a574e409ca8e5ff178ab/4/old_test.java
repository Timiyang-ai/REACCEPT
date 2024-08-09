    @Test
    public void expandFilterTest1() {
        LdapUser ldapUser = new LdapUser();
        ldapUser.setAttribute("mail", new TreeSet<>(Collections.singletonList("james@bond")));
        ldapUser.setAttribute("uid", new TreeSet<>(Collections.singletonList("bondjame")));
        ldapUser.setAttribute("ou", new TreeSet<>(Arrays.asList("MI6", "MI7")));
        User user = new User("007", "123", null, true);

        assertEquals("(objectclass=james@bond)",
                plugin.expandFilter("(objectclass=%mail%)", ldapUser, user));
        assertEquals("(objectclass=bondjame)",
                plugin.expandFilter("(objectclass=%uid%)", ldapUser, user));
        assertEquals("(objectclass=007)",
                plugin.expandFilter("(objectclass=%username%)", ldapUser, user));
        assertEquals("(objectclass=123)",
                plugin.expandFilter("(objectclass=%guid%)", ldapUser, user));

        ldapUser.setAttribute("role", new TreeSet<>(Collections.singletonList("agent")));
        assertEquals("(objectclass=agent)",
                plugin.expandFilter("(objectclass=%role%)", ldapUser, user));

        // doesn't work for more than one value
        ldapUser.setAttribute("role", new TreeSet<>(Arrays.asList("agent", "double-agent")));
        assertEquals("(objectclass=%role%)",
                plugin.expandFilter("(objectclass=%role%)", ldapUser, user));
    }