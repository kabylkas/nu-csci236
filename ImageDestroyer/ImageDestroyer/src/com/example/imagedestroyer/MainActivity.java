package com.example.imagedestroyer;

import java.io.ByteArrayOutputStream;






import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import Jama.Matrix;

public class MainActivity extends Activity {

	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	public Button imageButton;
	public Button seeButton;
	public ImageView imageView;
	
	public Bitmap photosc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		
		imageButton = new Button(this);
		seeButton = new Button(this);
		
		imageView = new ImageView(this);
		
		imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE); 
            }
        });
		
		
		seeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 imageView.setImageBitmap(photosc);
            }
        });
		
		
		LinearLayout mLinearLayout = new LinearLayout(this);
	    mLinearLayout.setOrientation(LinearLayout.VERTICAL);
	    mLinearLayout.addView(imageButton);
	    mLinearLayout.addView(imageView);
	    mLinearLayout.addView(seeButton);

	    
	    imageButton.setText("Capture Image");
	   
	    
	    setContentView(mLinearLayout);
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode ==  CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
            this.photosc = Bitmap.createScaledBitmap(photo, 300, 300, true);
            
            imageView.setImageBitmap(photosc);
            
            double[][] b = new double[900][100];
            
            int m = 0;
            for (int i = 0;i<300;i=i+10) {
            	for (int j = 0;j<300;j=j+10) {
            		for (int k = 0;k<10;k++) {
            			for (int l = 0;l<10;l++) {
            				b[m][k+(10*l)]=(double) (0x000000ff & photosc.getPixel(i+k, j+l));
            			}
            		}
            		
            		m++;
            	}
            	
            }
            
            Matrix B = new Matrix(b);
            
           Thread mThread = new Thread(new MatrixTask(imageView,B,photosc));
           mThread.start();
           
     
           
           
            
            // ByteArrayOutputStream photostream = new ByteArrayOutputStream();
            // photosc.compress(Bitmap.CompressFormat.PNG, 0, photostream);
            
            
        }  
    } 
}
