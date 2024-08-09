	@Test
	public void throwIfJvmFatal() {
		VirtualMachineError fatal1 = new VirtualMachineError() {};
		ThreadDeath fatal2 = new ThreadDeath();
		LinkageError fatal3 = new LinkageError();

		assertThatExceptionOfType(VirtualMachineError.class)
				.as("VirtualMachineError")
				.isThrownBy(() -> Exceptions.throwIfJvmFatal(fatal1))
				.isSameAs(fatal1);

		assertThatExceptionOfType(ThreadDeath.class)
				.as("ThreadDeath")
				.isThrownBy(() -> Exceptions.throwIfJvmFatal(fatal2))
				.isSameAs(fatal2);

		assertThatExceptionOfType(LinkageError.class)
				.as("LinkageError")
				.isThrownBy(() -> Exceptions.throwIfJvmFatal(fatal3))
				.isSameAs(fatal3);
	}