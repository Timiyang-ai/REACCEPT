@Override
	public String toString() {
		return Objects.toStringHelper(this).add("hashCode", Integer.toHexString(hashCode())).add("uuid", getUuid())
		        .omitNullValues().toString();
	}