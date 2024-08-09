public static TimeOfDay createTimeOfDay(String timeString) throws NumberFormatException,
    InstantiationException, IllegalAccessException {
    TimeOfDay time = TimeOfDay.class.newInstance()
        .setHours(Integer.decode(timeString.split(":")[0]))
        .setMinutes(Integer.decode(timeString.split(":")[1]))
        .setSeconds(Integer.decode(timeString.split(":")[2]));
    return time;
  }