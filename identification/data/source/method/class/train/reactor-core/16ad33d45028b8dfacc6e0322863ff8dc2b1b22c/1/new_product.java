public static void throwIfJvmFatal(@Nullable Throwable t) {
		if (t instanceof VirtualMachineError) {
			throw (VirtualMachineError) t;
		}
		if (t instanceof ThreadDeath) {
			throw (ThreadDeath) t;
		}
		if (t instanceof LinkageError) {
			throw (LinkageError) t;
		}
	}