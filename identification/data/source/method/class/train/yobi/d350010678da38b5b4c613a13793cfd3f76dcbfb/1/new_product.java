public static boolean isEmailExist(String emailAddress) {
        User user = find.where().ieq("email", emailAddress).findUnique();
        return user != null || Email.exists(emailAddress, true);
    }