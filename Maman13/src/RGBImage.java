public class RGBImage {

	private RGBColor[][] pixels;

	// ------------------------------------------------------------------------------
	/* OKAY */public RGBImage(int rows, int cols) {
		pixels = new RGBColor[rows][cols];
		setArrayBlack(pixels);

	}// CTOR1

	/* OKAY */public RGBImage(RGBColor[][] pixels) {
		setPixelArray(pixels);
	}// CTOR2

	/* OKAY */public RGBImage(RGBImage other) {
		this.pixels = copyArray(other.toRGBColorArray());
	}// CTOR3
		// -----------------------------------------------------------------------------

	/******************** METHODS BELOW *******************************/
	/* OKAY */public int getHeight() {
		return pixels.length;
	}// getHeight

	/* OKAY */public int getWidth() {
		return pixels[0].length;
	}// getWidth

	/* OKAY */public RGBColor getPixel(int row, int col) {
		if (row > getHeight() || col > getWidth()) {
			return new RGBColor();
		} else {
			return pixels[row][col];
		}

	}// getPixel

	/* OKAY */public void setPixel(int row, int col, RGBColor pixel) {
		if (row > getHeight() || col > getWidth()) {
			return;
		}// if
		else {
			pixels[row][col] = new RGBColor(pixel);
		}// else

	}// setPixel

	/* OKAY */public boolean equals(RGBImage other) {

		if (!(getHeight() == other.getHeight())
				|| !(getWidth() == other.getWidth())) {
			return false;
		}// if
		for (int i = 0; i < getHeight(); i++) {

			for (int j = 0; j < getWidth(); j++) {

				if (!pixels[i][j].equals(other.toRGBColorArray())) {
					return false;
				}// inner if

			}// inner loop

		}// outer loop
		return true;
	}// equals

	/* OKAY */public void flipHorizontal() {

		RGBColor[][] flippedImage = new RGBColor[getHeight()][getWidth()];

		for (int i = 0; i < flippedImage.length; i++) {

			flippedImage[i] = pixels[getWidth() - 1 - i];

		}// outer loop

		setPixelArray(flippedImage);
	}// flipHorizontal

	/* OKAY */public void flipVertical() {

		RGBColor[][] flippedImage = new RGBColor[getHeight()][getWidth()];

		for (int i = 0; i < flippedImage.length; i++) {

			for (int j = 0; j < flippedImage[0].length; j++) {

				flippedImage[i][j] = toRGBColorArray()[i][getWidth() - 1 - j];

			}// inner loop

		}// outer loop

		setPixelArray(flippedImage);
	}// flipVertical

	/* OKAY */public void invertColors() {
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				pixels[i][j].invert();
			}// inner loop
		}// outer loop
	}

	public RGBColor[][] toRGBColorArray() {
		return copyArray(copyArray(pixels));
	}// toRGBColorArray

	public void rotateClockwise() {

		RGBColor[][] rotatedArray = new RGBColor[getWidth()][getHeight()];

		for (int i = 0; i < rotatedArray.length; i++) {

			for (int j = 0; j < rotatedArray[0].length; j++) {

				rotatedArray[i][j] = toRGBColorArray()[getHeight() - 1 - j][i];

			}// inner loop

		}// outer loop
		setPixelArray(rotatedArray);
	}// rotateClockwise

	public void rotateCounterClockwise() {
		rotateClockwise();
		rotateClockwise();
		rotateClockwise();
	}// rotateCounterClockwise

	public void shiftCol(int offset) {
		if (offset == getHeight() || offset == -getHeight()) {
			setPixelArray(setArrayBlack(pixels));
		}// if equal to columns
		else if (offset > getHeight() - 1 || offset < -getHeight() + 1
				|| offset == 0) {
			return;
		}// if out of bounds
		else {
			RGBColor[][] blackSheet = setArrayBlack(pixels);
			if (offset > 0) {
				for (int i = 0; i < blackSheet.length; i++) {
					for (int j = 0; j < blackSheet[0].length; j++) {
						if (!(j < offset)) {
							blackSheet[i][j] = toRGBColorArray()[i][j - offset];
						}// if
					}// inner loop
				}// outer loop
				setPixelArray(blackSheet);
			}// if > 0
			else if (offset < 0) {
				flipVertical();
				offset = -offset;
				for (int i = 0; i < blackSheet.length; i++) {
					for (int j = 0; j < blackSheet[0].length; j++) {
						if (!(j < offset)) {
							blackSheet[i][j] = toRGBColorArray()[i][j - offset];
						}// if
					}// inner loop
				}// outer loop
				setPixelArray(blackSheet);
				flipVertical();
			}// else if
		}// else
	}// shiftCol

	public void shiftRow(int offset) {

	}// shiftRow

	public double[][] toGrayscaleArray() {
		return null;

	}// toGrayscaleArray

	private RGBColor[][] copyArray(RGBColor[][] pixels) {
		RGBColor[][] copiedArray = new RGBColor[pixels.length][pixels[0].length];
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {

				copiedArray[i][j] = pixels[i][j];

			}// inner loop
		}// outer loop
		return copiedArray;
	}// copyArray

	/* OKAY */private void setPixelArray(RGBColor[][] pixelArray) {
		this.pixels = copyArray(pixelArray);
	}// getRGBImage

	private RGBColor[][] setArrayBlack(RGBColor[][] sourceArray) {
		RGBColor[][] blackArray = new RGBColor[sourceArray.length][sourceArray[0].length];
		for (int i = 0; i < blackArray.length; i++) {
			for (int j = 0; j < blackArray[0].length; j++) {
				blackArray[i][j] = new RGBColor();

			}// inner loop
		}// outer loop
		return blackArray;
	}// setArrayBlack

}// class
