	@Test
	public void getNewOrderNumber_shouldAlwaysReturnUniqueOrderNumbersWhenCalledMultipleTimesWithoutSavingOrders()
	        throws InterruptedException {
		
		int N = 50;
		final Set<String> uniqueOrderNumbers = new HashSet<>(50);
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			threads.add(new Thread(() -> {
				try {
					Context.openSession();
					Context.addProxyPrivilege(PrivilegeConstants.ADD_ORDERS);
					uniqueOrderNumbers.add(((OrderNumberGenerator) orderService).getNewOrderNumber(null));
				}
				finally {
					Context.removeProxyPrivilege(PrivilegeConstants.ADD_ORDERS);
					Context.closeSession();
				}
			}));
		}
		for (int i = 0; i < N; ++i) {
			threads.get(i).start();
		}
		for (int i = 0; i < N; ++i) {
			threads.get(i).join();
		}
		//since we used a set we should have the size as N indicating that there were no duplicates
		Assert.assertEquals(N, uniqueOrderNumbers.size());
	}