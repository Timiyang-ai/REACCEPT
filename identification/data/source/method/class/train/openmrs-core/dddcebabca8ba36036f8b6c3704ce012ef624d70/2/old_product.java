@Override
	public boolean hasSameOrderableAs(Order otherOrder) {
		if (!super.hasSameOrderableAs(otherOrder)) {
			return false;
		}
		if (!(otherOrder instanceof DrugOrder)) {
			return false;
		}
		DrugOrder otherDrugOrder = (DrugOrder) otherOrder;
		return OpenmrsUtil.nullSafeEquals(this.getDrug(), otherDrugOrder.getDrug());
	}