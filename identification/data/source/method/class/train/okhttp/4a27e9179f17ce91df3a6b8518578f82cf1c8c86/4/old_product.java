public String decodePassword() {
    return password != null ? decode(password, 0, password.length()) : null;
  }