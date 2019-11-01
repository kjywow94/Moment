package com.travelmaker.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	public String getReviewImage(final int ImageNum) {
		String encodeImg = null;
		try {
			File f = new File("reviewImages/" + ImageNum);
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				f = new File("reviewImages/unknown.png");
				fis = new FileInputStream(f);
			}
			byte byteArray[] = new byte[(int) f.length()];
			fis.read(byteArray);
			encodeImg = "data:image/" + ImageNum + ";base64, " + Base64.getEncoder().encodeToString(byteArray);
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodeImg;

	}

}
