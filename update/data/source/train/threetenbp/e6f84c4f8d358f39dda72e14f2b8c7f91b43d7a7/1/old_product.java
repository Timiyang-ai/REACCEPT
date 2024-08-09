public ZonedDateTime atTime(LocalTime time) {
        return date.atTime(time).atZone(offset);
    }