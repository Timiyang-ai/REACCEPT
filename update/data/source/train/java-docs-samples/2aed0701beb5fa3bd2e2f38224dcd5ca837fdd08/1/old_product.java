public static Date createDate(String dateString) throws NumberFormatException,
    InstantiationException, IllegalAccessException {
    Date date = Date.class.newInstance().setYear(Integer.decode(dateString.split("-")[0]))
        .setMonth(Integer.decode(dateString.split("-")[1]))
        .setDay(Integer.decode(dateString.split("-")[2]));
    return date;
  }