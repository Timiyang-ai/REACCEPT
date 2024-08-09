@Test
    public void testToHumanizationDateString(){
        LOGGER.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date(
                        "2012-10-18 13:55:00",
                        DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date(
                        "2012-10-18 14:14:22",
                        DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date(
                        "2012-10-18 14:15:22",
                        DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date(
                        "2012-10-17 14:15:02",
                        DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date(
                        "2012-10-16 14:15:02",
                        DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date(
                        "2012-10-15 14:15:02",
                        DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date(
                        "2012-09-15 14:15:02",
                        DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(DateExtensionUtil.toHumanizationDateString(DateUtil.string2Date(
                        "2011-09-15 14:15:02",
                        DatePattern.COMMON_DATE_AND_TIME)));
        LOGGER.debug(DateExtensionUtil.toHumanizationDateString(DateUtil
                        .string2Date("2015-7-30 13:00:00", DatePattern.COMMON_DATE_AND_TIME)));
    }