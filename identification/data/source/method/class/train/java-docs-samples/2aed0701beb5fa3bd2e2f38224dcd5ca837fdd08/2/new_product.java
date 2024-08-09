public static Date createDate(String dateString)
      throws NumberFormatException, InstantiationException, IllegalAccessException {
    Date date =
        Date.class
            .newInstance()
            .setYear(Integer.parseInt(dateString.split("-")[0], BASE_10))
            .setMonth(Integer.parseInt(dateString.split("-")[1], BASE_10))
            .setDay(Integer.parseInt(dateString.split("-")[2], BASE_10));
    return date;
  }