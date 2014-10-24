package com.example.imagedestroyer;

import android.graphics.Bitmap;
import android.widget.ImageView;
import Jama.Matrix;

public class MatrixTask implements Runnable {

	private ImageView iv;
	private Matrix B;
	private Bitmap photo;
	
	public MatrixTask(ImageView imageView, Matrix B,Bitmap photosc) {
		this.iv = imageView;
		this.B = B;
		this.photo = photosc;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	
         
         System.out.println("Finished!");

	}

}
