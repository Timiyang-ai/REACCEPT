@Override
	public boolean hasSameOrderableAs(Order otherOrder) {
		if (!super.hasSameOrderableAs(otherOrder)) {
			return false;
		}
		if (!(otherOrder instanceof DrugOrder)) {
			return false;
		}
		DrugOrder otherDrugOrder = (DrugOrder) otherOrder;

		if (isNonCodedDrug() || otherDrugOrder.isNonCodedDrug()) {
			return OpenmrsUtil.nullSafeEqualsIgnoreCase(this.getDrugNonCoded(), otherDrugOrder.getDrugNonCoded());
		}
		return OpenmrsUtil.nullSafeEquals(this.getDrug(), otherDrugOrder.getDrug());
	}