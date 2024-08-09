public int vowelCount(String str) {
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      if (isVowel(str.charAt(i))) {
        count++;
      }
    }
    return count;
  }