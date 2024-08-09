@Override
	public LocationTag retireLocationTag(LocationTag tag, String reason) throws APIException {
		if (tag.isRetired()) {
			return tag;
		} else {
			if (reason == null) {
				throw new APIException("Location.retired.reason.required", (Object[]) null);
			}
			tag.setRetired(true);
			tag.setRetireReason(reason);
			tag.setRetiredBy(Context.getAuthenticatedUser());
			tag.setDateRetired(new Date());
			return Context.getLocationService().saveLocationTag(tag);
		}
	}