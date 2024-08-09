static
	public Object cast(DataType dataType, Object value){

		switch(dataType){
			case STRING:
				return toString(value);
			case INTEGER:
				return toInteger(value);
			case FLOAT:
				return toFloat(value);
			case DOUBLE:
				return toDouble(value);
			case BOOLEAN:
				return toBoolean(value);
			case DATE:
				return toDate(value);
			case TIME:
				return toTime(value);
			case DATE_TIME:
				return toDateTime(value);
			case DATE_DAYS_SINCE_1960:
				return toDaysSinceDate(value, YEAR_1960);
			case DATE_DAYS_SINCE_1970:
				return toDaysSinceDate(value, YEAR_1970);
			case DATE_DAYS_SINCE_1980:
				return toDaysSinceDate(value, YEAR_1980);
			case TIME_SECONDS:
				return toSecondsSinceMidnight(value);
			case DATE_TIME_SECONDS_SINCE_1960:
				return toSecondsSinceDate(value, YEAR_1960);
			case DATE_TIME_SECONDS_SINCE_1970:
				return toSecondsSinceDate(value, YEAR_1970);
			case DATE_TIME_SECONDS_SINCE_1980:
				return toSecondsSinceDate(value, YEAR_1980);
			default:
				throw new UnsupportedFeatureException();
		}
	}