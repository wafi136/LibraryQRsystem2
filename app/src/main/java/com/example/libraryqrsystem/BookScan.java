package com.example.libraryqrsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import java.util.List;

public class BookScan extends AppCompatActivity {
    CameraView cameraEye;
    boolean Shown = false;
    Button PressAgain;

    FirebaseVisionBarcodeDetectorOptions options;
    FirebaseVisionBarcodeDetector detector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_scan);

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        setupCamera();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();
    }

    private void setupCamera() {
        PressAgain = (Button)findViewById(R.id.btn_redo);
        PressAgain.setEnabled(Shown);
        PressAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shown = !Shown;
                PressAgain.setEnabled(Shown);
            }
        });

        cameraEye = (CameraView)findViewById(R.id.cameraView);
        cameraEye.setLifecycleOwner(this);
        cameraEye.addFrameProcessor(new FrameProcessor() {
            @Override
            public void process(@NonNull Frame frame) {
                processImage(getVisionImageFromFrame(frame));
            }
        });

        options = new FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
                .build();
        detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options);
    }

    private void processImage(FirebaseVisionImage image) {
        if(!Shown)
        {
            detector.detectInImage(image)
                    .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                        @Override
                        public void onSuccess(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
                            processResult(firebaseVisionBarcodes);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(BookScan.this,"Ida mau"+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void processResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
        if(firebaseVisionBarcodes.size()>0)
        {
            Shown = true;
            PressAgain.setEnabled(Shown);
            for(FirebaseVisionBarcode item: firebaseVisionBarcodes)
            {
                int value_type = item.getValueType();
                switch (value_type)
                {
                    case FirebaseVisionBarcode.TYPE_TEXT:
                    {   //Data to be send to next activity
                        createDialog(item.getRawValue());
                        Intent passData = new Intent(BookScan.this,BorrowingProcess.class);
                        passData.putExtra("Book",item.getRawValue());
                        startActivity(passData);
                        finish();
                    }
                    break;
                    case FirebaseVisionBarcode.TYPE_URL:
                    {
                        //START browser intent
                        Intent Website = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getRawValue()));
                        startActivity(Website);
                    }
                    break;
                    default:
                        break;
                }
            }
        }
    }

    private void createDialog(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private FirebaseVisionImage getVisionImageFromFrame(Frame frame) {
        byte[] data = frame.getData();
        FirebaseVisionImageMetadata metadata = new FirebaseVisionImageMetadata.Builder()
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                .setHeight(frame.getSize().getHeight())
                .setWidth(frame.getSize().getWidth())
                .build();
        return FirebaseVisionImage.fromByteArray(data,metadata);





    }



}
