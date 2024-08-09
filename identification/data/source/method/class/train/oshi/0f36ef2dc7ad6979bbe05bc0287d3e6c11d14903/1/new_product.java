public static String formatBytes(long bytes) {
		if (bytes == 1) { // bytes
			return String.format("%d byte", bytes);
		} else if (bytes < kibiByte) { // bytes
			return String.format("%d bytes", bytes);
		} else if (bytes < mebiByte && bytes % kibiByte == 0) { // KiB
			return String.format("%.0f KiB", (double) bytes / kibiByte);
		} else if (bytes < mebiByte) { // KiB
			return String.format("%.1f KiB", (double) bytes / kibiByte);
		} else if (bytes < gibiByte && bytes % mebiByte == 0) { // MiB
			return String.format("%.0f MiB", (double) bytes / mebiByte);
		} else if (bytes < gibiByte) { // MiB
			return String.format("%.1f MiB", (double) bytes / mebiByte);
		} else if (bytes % gibiByte == 0 && bytes < tebiByte) { // GiB
			return String.format("%.0f GiB", (double) bytes / gibiByte);
		} else if (bytes < tebiByte) { // GiB
			return String.format("%.1f GiB", (double) bytes / gibiByte);
		} else if (bytes % tebiByte == 0 && bytes < pebiByte) { // TiB
			return String.format("%.0f TiB", (double) bytes / tebiByte);
		} else if (bytes < pebiByte) { // TiB
			return String.format("%.1f TiB", (double) bytes / tebiByte);
		} else if (bytes % pebiByte == 0 && bytes < exbiByte) { // PiB
			return String.format("%.0f PiB", (double) bytes / pebiByte);
		} else if (bytes < exbiByte) { // PiB
			return String.format("%.1f PiB", (double) bytes / pebiByte);
		} else {
			return String.format("%d bytes", bytes);
		}
	}