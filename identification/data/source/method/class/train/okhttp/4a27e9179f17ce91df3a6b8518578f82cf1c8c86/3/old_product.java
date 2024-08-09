public String decodePassword() {
    return password != null ? percentDecode(password) : null;
  }