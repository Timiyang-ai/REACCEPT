public void dispose () {
		GL20 gl = Gdx.gl20;

		IntBuffer handle = BufferUtils.newIntBuffer(1);

		colorTexture.dispose();
		if (hasDepth) {
			handle.put(depthbufferHandle);
			handle.flip();
			gl.glDeleteRenderbuffers(1, handle);
		}

		if (hasStencil) {
			handle.put(stencilbufferHandle);
			handle.flip();
			gl.glDeleteRenderbuffers(1, handle);
		}

		handle.clear();
		handle.put(framebufferHandle);
		handle.flip();
		gl.glDeleteFramebuffers(1, handle);

		if (buffers.get(Gdx.app) != null) buffers.get(Gdx.app).removeValue(this, true);
	}