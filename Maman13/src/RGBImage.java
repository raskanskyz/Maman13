public class RGBImage {

	private RGBColor[][] pixels;
	private int height;
	private int width;

	// ------------------------------------------------------------------------------
	/* OKAY */public RGBImage(int rows, int cols) {
		setHeight(rows);
		setWidth(cols);
		pixels = new RGBColor[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pixels[i][j] = new RGBColor();
			}// inner loop
		}// outer loop
	}// CTOR1

	/* OKAY */public RGBImage(RGBColor[][] pixels) {
		setHeight(pixels.length);
		setWidth(pixels[0].length);
	}// CTOR2

	/* OKAY */public RGBImage(RGBImage other) {
		RGBImage image = new RGBImage(other.getRGBImage());
		setRGBImage(image.getRGBImage());
		setHeight(image.getHeight());
		setWidth(image.getWidth());
	}// CTOR3
		// -----------------------------------------------------------------------------

	/******************** METHODS BELOW *******************************/
	public int getHeight() {
		return height;
	}// getHeight

	public void setHeight(int height) {
		this.height = height;
	}// setHeight

	public int getWidth() {
		return width;
	}// getWidth

	public void setWidth(int width) {
		this.width = width;
	}// setWidth

	/* OKAY */public RGBColor getPixel(int row, int col) {
		if (row > getHeight() || col > getWidth()) {
			return new RGBColor();
		} else {
			return pixels[row][col];
		}

	}// getPixel

	public void setPixel(int row, int col, RGBColor pixel) {
		if (row > getHeight() || col > getWidth()) {
			return;
		}// if
		else {
			pixels[row][col] = pixel;
			// should i do this instead--->pixels[row][col]= new
			// RGBColor(pixel);
		}// else

	}// setPixel

/* OKAY */	public boolean equals(RGBImage other) {

		if (!(getHeight() == other.getHeight())
				|| !(getWidth() == other.getWidth())) {
			return false;
		}// if
		for (int i = 0; i < getHeight(); i++) {

			for (int j = 0; j < getWidth(); j++) {

				if (!pixels[i][j].equals(other.getRGBImage()[i][j])) {
					return false;
				}// inner if

			}// inner loop

		}// outer loop
		return true;
	}// equals

	public void setRGBImage(RGBColor[][] pixels) {
		this.pixels = pixels;
	}// setRGBImage

	public RGBColor[][] getRGBImage() {
		return pixels;
	}// getRGBImage

}// class
