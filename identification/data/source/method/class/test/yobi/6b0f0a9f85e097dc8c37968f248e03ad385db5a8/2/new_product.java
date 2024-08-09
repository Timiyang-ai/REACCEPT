public static User findByLoginId(String loginId) {
        User user = find.where().eq("loginId", loginId).findUnique();
        if(  user == null ) {
            return anonymous;
        } else {
            return user;
        }
    }