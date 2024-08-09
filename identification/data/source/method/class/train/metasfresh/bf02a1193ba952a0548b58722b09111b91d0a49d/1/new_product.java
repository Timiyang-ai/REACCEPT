public Quantity add(@NonNull final Quantity qtyToAdd)
	{
		if (qtyToAdd.isZero())
		{
			return this;
		}

		// Get QtyToAdd value (mandatory)
		final BigDecimal qtyToAdd_Value;
		final int uomId = this.getUOMId();
		final int qtyToAdd_uomId = qtyToAdd.getUOMId();
		final int qtyToAdd_sourceUomId = qtyToAdd.getSource_UOM_ID();
		if (uomId == qtyToAdd_uomId)
		{
			qtyToAdd_Value = qtyToAdd.getQty();
		}
		else if (uomId == qtyToAdd_sourceUomId)
		{
			qtyToAdd_Value = qtyToAdd.getSourceQty();
		}
		else
		{
			throw new QuantitiesUOMNotMatchingExpection("Cannot add " + qtyToAdd + " to " + this + " because UOMs are not compatible");
		}

		//
		// Get QtyToAdd source value
		final BigDecimal qtyToAdd_SourceValue;
		final int sourceUomId = this.getSource_UOM_ID();
		if (sourceUomId == qtyToAdd_sourceUomId)
		{
			qtyToAdd_SourceValue = qtyToAdd.getSourceQty();
		}
		else if (sourceUomId == qtyToAdd_uomId)
		{
			qtyToAdd_SourceValue = qtyToAdd.getQty();
		}
		else
		{
			// Source UOM does not match. We can skip it
			qtyToAdd_SourceValue = null;
		}

		//
		// Compute new Quantity's values
		final BigDecimal qtyNew_Value = this.getQty().add(qtyToAdd_Value);
		final I_C_UOM qtyNew_UOM = this.getUOM();
		final BigDecimal qtyNew_SourceValue;
		final I_C_UOM qtyNew_SourceUOM;
		if (qtyToAdd_SourceValue == null)
		{
			qtyNew_SourceValue = qtyNew_Value;
			qtyNew_SourceUOM = qtyNew_UOM;
		}
		else
		{
			qtyNew_SourceValue = this.getSourceQty().add(qtyToAdd_SourceValue);
			qtyNew_SourceUOM = this.getSourceUOM();
		}

		//
		// Create the new quantity and return it
		return new Quantity(qtyNew_Value, qtyNew_UOM, qtyNew_SourceValue, qtyNew_SourceUOM);
	}