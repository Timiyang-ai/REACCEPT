public int vowelCount(String s) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      if (isVowel(s.charAt(i))) {
        result++;
      }
    }
    return result;
  }