package service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class CameraService extends Service {
	// Camera variables
	// a surface holder
	private SurfaceHolder sHolder;
	// a variable to control the camera
	private Camera mCamera;
	String mob1="";
	
	SharedPreferences sp;
	// the camera parameters
	private Parameters parameters;
	private Context context;

	private boolean hasCamera;
	private int cameraId;
	String rootPath = "";
	Mail m;

	/** Called when the activity is first created. */
	@Override
	public void onCreate() {
		super.onCreate();

	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub

		super.onStart(intent, startId);

		rootPath = Environment.getExternalStorageDirectory() + "";
		Toast.makeText(CameraService.this, "Service started", Toast.LENGTH_LONG)
				.show();
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		
		sp = getSharedPreferences(Globals.PREFERENCES_NAME, Context.MODE_PRIVATE);
		mob1 = sp.getString(Globals.phone1, "");
		
		
		
		if (getApplicationContext().getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			cameraId = getFrontCameraId();

			if (cameraId != -1) {
				hasCamera = true;
			} else {
				hasCamera = false;
			}
		} else {
			hasCamera = false;
		}

		if (hasCamera) {
			mCamera = Camera.open(cameraId);
			SurfaceView sv = new SurfaceView(getApplicationContext());

			try {
				mCamera.setPreviewDisplay(sv.getHolder());
				parameters = mCamera.getParameters();

//				List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
//				Camera.Size ps = previewSizes.get(0);
//				parameters.setPreviewSize(ps.width, ps.height);
				// set camera parameters
				mCamera.setParameters(parameters);
				
				

				mCamera.startPreview();
			
				
				mCamera.takePicture(null, null, mCall);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Get a surface
			sHolder = sv.getHolder();
			
			sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			
		}
	}

		Camera.PictureCallback mCall = new Camera.PictureCallback() {

			public void onPictureTaken(byte[] data, Camera camera) {
				// decode the data obtained by the camera into a Bitmap

				FileOutputStream outStream = null;
				try {
					outStream = new FileOutputStream(rootPath + "//"
							+ "www.jpg");
					
					Log.d("Path", rootPath);
					outStream.write(data);
					outStream.close();
//					
//					sendMMS();
//					
					
					String img=HexEncodeDecode.encode(data);
					String result=WebServiceClient.sendimage(Globals.LOGIN_ID,img);
					
					if(m==null)
					{
						m=new Mail(sp.getString(Globals.emailid, ""), Globals.userEmail, Globals.userPass);
					}
					
					try {
						m.addAttachment(rootPath+"//www.jpg");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						if (m.send()) {
							
//									Toast.makeText(LocationTracker.this,
//											"Email was sent successfully.",
//											Toast.LENGTH_LONG).show();
									Log.v("Email", "Email Sent Successfully");
									

						} else {
						
//									Toast.makeText(LocationTracker.this,
//											"Email was not sent", Toast.LENGTH_LONG)
//											.show();
									Log.v("Email", "Email was not sent");
								}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (FileNotFoundException e) {
					Log.d("CAMERA", e.getMessage());
				} catch (IOException e) {
					Log.d("CAMERA", e.getMessage());
				}

			}
		};

		
		public void sendMMS()
		{
			   try {
		           
		            Uri uri = Uri.parse("file://"+rootPath + "//"
							+ "www.jpg");
		            Intent i = new Intent(Intent.ACTION_SEND);
		            i.putExtra("address",mob1);
		            i.putExtra("sms_body","Caughted Image");
		            i.putExtra(Intent.EXTRA_STREAM,uri);
		            i.setType("image/png");
		            startActivity(i);
		        } catch (Exception e) {
		            // TODO: handle exception
		            e.printStackTrace();
		        }
		}
		
	private int getFrontCameraId() {
		int camId = -1;
		int numberOfCameras = Camera.getNumberOfCameras();
		CameraInfo ci = new CameraInfo();

		for (int i = 0; i < numberOfCameras; i++) {
			Camera.getCameraInfo(i, ci);
			if (ci.facing == CameraInfo.CAMERA_FACING_FRONT) {
				camId = i;
//				mCamera=Camera.open(camId);
			}
		}

		return camId;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
