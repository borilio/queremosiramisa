package com.inserta.queremosiramisa.clases;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GeneradorQR {
	
	public static String generarQR(String text) {
		
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix matrix;
		try {
			matrix = writer.encode(text, com.google.zxing.BarcodeFormat.QR_CODE, 300, 300); //alto=300 y ancho=300. Puede modificarse si fuera necesario
		} catch (WriterException e) {
			System.out.println("Hubo un error al crear el código QR. Excepción: " + e.toString());
			return null;
		}

        BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrix.getWidth(), matrix.getHeight());
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrix.getWidth(); i++) {
            for (int j = 0; j < matrix.getHeight(); j++) {
                if (matrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

		
        //Ya que tengo la imagen (en BufferReader), la convertimos a Base64 y eso es lo que devolvemos, un String...
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			ImageIO.write(image, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
        //Lo convertimos a imagen en Base64
        String imagenComoBase64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(output.toByteArray());
        
		return imagenComoBase64;
	}
	
}
