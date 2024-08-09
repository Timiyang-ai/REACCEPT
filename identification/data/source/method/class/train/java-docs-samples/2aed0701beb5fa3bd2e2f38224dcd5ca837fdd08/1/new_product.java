public static TimeOfDay createTimeOfDay(String timeString)
      throws NumberFormatException, InstantiationException, IllegalAccessException {
    TimeOfDay time =
        TimeOfDay.class
            .newInstance()
            .setHours(Integer.parseInt(timeString.split(":")[0], BASE_10))
            .setMinutes(Integer.parseInt(timeString.split(":")[1], BASE_10))
            .setSeconds(Integer.parseInt(timeString.split(":")[2], BASE_10));
    return time;
  }