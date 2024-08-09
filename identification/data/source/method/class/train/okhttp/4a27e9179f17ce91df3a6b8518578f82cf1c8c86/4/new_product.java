public String decodePassword() {
    return password != null ? percentDecode(password, 0, password.length()) : null;
  }