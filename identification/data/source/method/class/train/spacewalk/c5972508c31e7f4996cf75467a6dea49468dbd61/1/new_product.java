public static Profile create(CobblerConnection client, 
                                String name, Distro distro) {
        Profile profile = new Profile(client);
        profile.handle = (String) client.invokeTokenMethod("new_profile");
        profile.modify(NAME, name);
        profile.setDistro(distro);
        profile.save();
        profile = lookupByName(client, name);
        return profile;
    }