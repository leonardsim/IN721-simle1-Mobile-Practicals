package bit.simle1.photomosaic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String mPhotoFileName = "";
    File mPhotoFile;
    Uri mPhotoFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create button reference and set it
        Button btnMosaic = (Button) findViewById(R.id.btnMosaic);
        btnMosaic.setOnClickListener(new setMosaic());
    }

    // Event Handler
    private class setMosaic implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            // Creates time stamped file to hold the image data
            mPhotoFile = createTimeStampedFile();

            // Generate Uri from the File instance
            mPhotoFileUri = Uri.fromFile(mPhotoFile);

            // Create an intent for the image capture content provider
            Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // Attach Uri to the intent
            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoFileUri);

            // Launch the intent
            // The user will see the camera app. When they finish, onActivityResult is raised
            startActivityForResult(imageCaptureIntent, 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // The return key set from startActivityForResult
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK) // RESULT_OK = -1
            {
                // File path for BitmapFactory
                String realFilePath = mPhotoFile.getPath();

                Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilePath);

                // Set image view here
                setImageViews(userPhotoBitmap);
            }
            else
            {
                Toast.makeText(this, "No photo saved. You dun goofd", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Method
    private File createTimeStampedFile()
    {
        // Fetch system image folder
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        // Make subdirectory
        File imageStorageDirectory = new File(imageRootPath, "CameraMosaic");

        if (!imageStorageDirectory.exists())
        {
            imageStorageDirectory.mkdirs(); // mkdirs creates parent directories as required
        }

        // Get timestamp
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currenTime = new Date();
        String timeStamp = timeStampFormat.format(currenTime);

        // Make file name
        mPhotoFileName = "IMG_" + timeStamp + ".jpg";

        // Make file object from directory and filename
        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + mPhotoFileName);

        return photoFile;
    }

    private void setImageViews(Bitmap bm)
    {
        // Create image view references
        ImageView iv1 = (ImageView) findViewById(R.id.iv1);
        ImageView iv2 = (ImageView) findViewById(R.id.iv2);
        ImageView iv3 = (ImageView) findViewById(R.id.iv3);
        ImageView iv4 = (ImageView) findViewById(R.id.iv4);

        // Set the images
        iv1.setImageBitmap(bm);
        iv2.setImageBitmap(bm);
        iv3.setImageBitmap(bm);
        iv4.setImageBitmap(bm);
    }
}
