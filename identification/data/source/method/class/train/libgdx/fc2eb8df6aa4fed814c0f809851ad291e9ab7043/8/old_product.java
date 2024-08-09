public void dispose () {
		GL20 gl = Gdx.gl20;

		IntBuffer handle = BufferUtils.newIntBuffer(1);

		colorTexture.dispose();
		if (hasDepth)
			gl.glDeleteRenderbuffer(depthbufferHandle);

		if (hasStencil)
			gl.glDeleteRenderbuffer(stencilbufferHandle);

		gl.glDeleteFramebuffer(framebufferHandle);

		if (buffers.get(Gdx.app) != null) buffers.get(Gdx.app).removeValue(this, true);
	}