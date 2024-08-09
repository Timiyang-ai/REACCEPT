@SuppressWarnings({"ConstantConditions", "SuspiciousSystemArraycopy"})
    public static Object convert(Object val, GridH2RowDescriptor desc, Class<?> expCls,
        int type, String columnName) {
        if (val == null)
            return null;

        Class<?> currCls = val.getClass();

        try {
            // H2 thinks that java.util.Date is always a Timestamp, while binary marshaller expects
            // precise Date instance. Let's satisfy it.
            if (val instanceof Date && currCls != Date.class && expCls == Date.class)
                return new Date(((Date) val).getTime());

            // User-given UUID is always serialized by H2 to byte array, so we have to deserialize manually
            if (type == Value.UUID && currCls == byte[].class) {
                return U.unmarshal(desc.context().marshaller(), (byte[])val,
                    U.resolveClassLoader(desc.context().gridConfig()));
            }

            if (val instanceof Timestamp && LocalDateTimeUtils.LOCAL_DATE_TIME == expCls)
                return LocalDateTimeUtils.valueToLocalDateTime(ValueTimestamp.get((Timestamp)val));

            if (val instanceof Date && LocalDateTimeUtils.LOCAL_DATE == expCls) {
                return LocalDateTimeUtils.valueToLocalDate(ValueDate.fromDateValue(
                    DateTimeUtils.dateValueFromDate(((Date)val).getTime())));
            }

            if (val instanceof Time && LocalDateTimeUtils.LOCAL_TIME == expCls)
                return LocalDateTimeUtils.valueToLocalTime(ValueTime.get((Time)val));

            // We have to convert arrays of reference types manually -
            // see https://issues.apache.org/jira/browse/IGNITE-4327
            // Still, we only can convert from Object[] to something more precise.
            if (type == Value.ARRAY && currCls != expCls) {
                if (currCls != Object[].class) {
                    throw new IgniteCheckedException("Unexpected array type - only conversion from Object[] " +
                        "is assumed");
                }

                // Why would otherwise type be Value.ARRAY?
                assert expCls.isArray();

                Object[] curr = (Object[])val;

                Object newArr = Array.newInstance(expCls.getComponentType(), curr.length);

                System.arraycopy(curr, 0, newArr, 0, curr.length);

                return newArr;
            }

            Object res = H2Utils.convert(val, desc.indexing(), type);

            // We can get a Timestamp instead of Date when converting a String to Date
            // without query - let's handle this
            if (res instanceof Date && res.getClass() != Date.class && expCls == Date.class)
                return new Date(((Date)res).getTime());

            return res;
        }
        catch (Exception e) {
            throw new IgniteSQLException("Value conversion failed [column=" + columnName + ", from=" + currCls.getName() + ", to=" +
                expCls.getName() +']', IgniteQueryErrorCode.CONVERSION_FAILED, e);
        }
    }