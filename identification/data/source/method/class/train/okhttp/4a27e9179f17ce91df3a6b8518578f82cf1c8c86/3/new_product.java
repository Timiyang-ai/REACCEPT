public String decodePassword() {
    return password != null ? percentDecode(password, false) : null;
  }