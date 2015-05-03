package com.example.projectapplicationmajor8;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	static ImageView iv;
	SeekBar bar1, bar2;
	Bitmap photo;
	public static int progress;
//edited by alterigo
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.imageView1);
		bar1 = (SeekBar) findViewById(R.id.seekBar1);
		bar2 = (SeekBar) findViewById(R.id.seekBar2);

		BitmapDrawable abmp = (BitmapDrawable) iv.getDrawable();
		photo = abmp.getBitmap();
		bar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int progress,
					boolean arg2) {
				// TODO Auto-generated method stub

				doBrightness(photo, progress);

			}
		});
		bar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int progress,
					boolean arg2) {
				// TODO Auto-generated method stub
				doBrightness(photo, -progress);
			}
		});
	}

	public static void doBrightness(Bitmap src, int value) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;

		// scan through all pixels
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);

				// increase/decrease each channel
				R -= value;
				if (R > 255) {
					R = 255;
				} else if (R < 0) {
					R = 0;
				}

				G -= value;
				if (G > 255) {
					G = 255;
				} else if (G < 0) {
					G = 0;
				}

				B -= value;
				if (B > 255) {
					B = 255;
				} else if (B < 0) {
					B = 0;
				}

				// apply new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}
		iv.setImageBitmap(bmOut);
	}

}
