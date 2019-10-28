package com.travelmaker.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.stereotype.Service;

import com.travelmaker.dto.ReviewImage;

@Service
public class ReviewImageService {

	public boolean uploadImage(final ReviewImage image) {
		String imageData = image.getImageData();
		int imageName = image.getImageNum();
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte = decoder.decode(imageData.split(",")[1]);
		try {
			FileOutputStream fos = new FileOutputStream("reviewImages/" + imageName);
			fos.write(decodedByte);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
