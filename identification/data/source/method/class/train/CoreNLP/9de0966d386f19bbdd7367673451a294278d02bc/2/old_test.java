  private static <C extends Comparable> void compare(C[] a1, C[] a2) {
    System.out.printf("compare(%s, %s) = %d%n",
                      Arrays.toString(a1),
                      Arrays.toString(a2),
                      ArrayUtils.compareArrays(a1, a2));
  }