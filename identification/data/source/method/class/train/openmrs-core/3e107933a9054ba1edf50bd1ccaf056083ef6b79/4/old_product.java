@SuppressWarnings("unchecked")
	public boolean equalsContent(PersonAddress otherAddress) {
		return new EqualsBuilder().append(defaultString(otherAddress.getAddress1()), defaultString(address1)).append(
		    defaultString(otherAddress.getAddress2()), defaultString(address2)).append(
		    defaultString(otherAddress.getAddress3()), defaultString(address3)).append(
		    defaultString(otherAddress.getAddress4()), defaultString(address4)).append(
		    defaultString(otherAddress.getAddress5()), defaultString(address5)).append(
		    defaultString(otherAddress.getAddress6()), defaultString(address6)).append(
		    defaultString(otherAddress.getCityVillage()), defaultString(cityVillage)).append(
		    defaultString(otherAddress.getCountyDistrict()), defaultString(countyDistrict)).append(
		    defaultString(otherAddress.getStateProvince()), defaultString(stateProvince)).append(
		    defaultString(otherAddress.getCountry()), defaultString(country)).append(
		    defaultString(otherAddress.getPostalCode()), defaultString(postalCode)).append(
		    defaultString(otherAddress.getLatitude()), defaultString(latitude)).append(
		    defaultString(otherAddress.getLongitude()), defaultString(longitude)).append(otherAddress.getStartDate(),
		    startDate).append(otherAddress.getEndDate(), endDate).isEquals();
	}