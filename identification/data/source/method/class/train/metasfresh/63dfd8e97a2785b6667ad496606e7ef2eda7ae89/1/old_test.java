	@Test
	void createFor()
	{
		final I_C_BPartner headOfSalesRecord = newInstance(I_C_BPartner.class);
		saveRecord(headOfSalesRecord);

		final I_C_BPartner salesSuperVisor = newInstance(I_C_BPartner.class);
		salesSuperVisor.setC_BPartner_SalesRep_ID(headOfSalesRecord.getC_BPartner_ID());
		saveRecord(salesSuperVisor);

		final I_C_BPartner salesRep1 = newInstance(I_C_BPartner.class);
		salesRep1.setC_BPartner_SalesRep_ID(salesSuperVisor.getC_BPartner_ID());
		saveRecord(salesRep1);

		// sibling of salesRep1; shall not be part of salesRep1's hierachy
		final I_C_BPartner salesRep2 = newInstance(I_C_BPartner.class);
		salesRep2.setC_BPartner_SalesRep_ID(salesSuperVisor.getC_BPartner_ID());
		saveRecord(salesRep2);

		// add a cycle to make sure the code can handle it
		headOfSalesRecord.setBPartner_Parent_ID(salesRep1.getC_BPartner_ID());
		saveRecord(headOfSalesRecord);

		// invoke the method under test
		final Hierarchy result = new CommissionHierarchyFactory().createFor(BPartnerId.ofRepoId(salesRep1.getC_BPartner_ID()));

		assertThat(result.getParent(node(salesRep2.getC_BPartner_ID()))).isNotPresent();
		assertThat(result.getParent(node(salesRep1.getC_BPartner_ID()))).contains(node(salesSuperVisor.getC_BPartner_ID()));
		assertThat(result.getParent(node(salesSuperVisor.getC_BPartner_ID()))).contains(node(headOfSalesRecord.getC_BPartner_ID()));
		assertThat(result.getParent(node(headOfSalesRecord.getC_BPartner_ID()))).isNotPresent();
	}