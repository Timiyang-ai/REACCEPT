public static KeyNamePair[] getKeyNamePairs(String sql, boolean optional, Object ... params)
	{
		return getKeyNamePairs(null, sql, optional, params);
	}