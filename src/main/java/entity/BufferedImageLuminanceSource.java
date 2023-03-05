package entity;

import com.google.zxing.LuminanceSource;

import java.awt.image.BufferedImage;

public class BufferedImageLuminanceSource extends LuminanceSource {
    private final BufferedImage image;

    public BufferedImageLuminanceSource(BufferedImage image) {
        super(image.getWidth(), image.getHeight());
        this.image = image;
    }

    @Override
    public byte[] getRow(int y, byte[] row) {
        int width = getWidth();
        if (row == null || row.length < width) {
            row = new byte[width];
        }
        image.getRaster().getDataElements(0, y, width, 1, row);
        return row;
    }

    @Override
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        byte[] matrix = new byte[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            getRow(y, matrix, offset);
        }
        return matrix;
    }

    private void getRow(int y, byte[] matrix, int offset) {
        int width = getWidth();
        image.getRaster().getDataElements(0, y, width);
    }
}
