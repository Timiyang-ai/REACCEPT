public boolean equalWithTolerance(PointSensitivities other, double tolerance) {
    ImmutableList<PointSensitivity> list1 = this.getSensitivities();
    ImmutableList<PointSensitivity> list2 = other.getSensitivities();
    int nbList1 = list1.size();
    int nbList2 = list2.size();
    if (nbList1 != nbList2) {
      return false;
    }
    for (int i1 = 0; i1 < nbList1; i1++) {
      if (list1.get(i1).compareExcludingSensitivity(list2.get(i1)) == 0) {
        if (Math.abs(list1.get(i1).getSensitivity() - list2.get(i1).getSensitivity()) > tolerance) {
          return false;
        }
      } else {
        return false;
      }
    }
    return true;
  }