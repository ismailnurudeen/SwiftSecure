package com.ss.swiftsecure;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class AddReportActivity extends AppCompatActivity {
    private final int UPLOAD_IMAGE_REQUEST = 95;
    private EditText descriptionInput,addressInput;
    private Spinner  crimeCategorySpinner;
    private Button uploadImageBtn;
    private Button postReportBtn;
    private CheckBox useMyInfoCheckbox;

    private ImageView selectedReportImageView;
    private String descriptionValue, addressValue,crimeTypeValue;
    private boolean useMyContact = true;
    private Uri imageUri;
    private String contactEmailValue;
    private byte[] imageValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reports);

        /*Fetching form from add_report.xml*/

        descriptionInput = findViewById(R.id.new_house_desc_et);
        addressInput = findViewById(R.id.new_house_address_et);

        crimeCategorySpinner = findViewById(R.id.new_house_type_spin);

        uploadImageBtn = findViewById(R.id.upload_image_btn);
        postReportBtn = findViewById(R.id.add_new_house_btn);
        useMyInfoCheckbox = findViewById(R.id.new_house_use_my_contact);
        useMyInfoCheckbox.setChecked(useMyContact);

        useMyInfoCheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO: Implement this method
                if (useMyContact) {

                } else {

                }
                useMyContact = !useMyContact;
            }
        });

        selectedReportImageView = findViewById(R.id.addnewhouse_Image);

        postReportBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                descriptionValue = descriptionInput.getText().toString();
                addressValue = addressInput.getText().toString();
                crimeTypeValue = crimeCategorySpinner.getSelectedItem().toString();
                if (useMyContact) {

                } else {

                }

                if (TextUtils.isEmpty(descriptionValue) || TextUtils.isEmpty(addressValue)) {
                    Toast.makeText(AddReportActivity.this, "No field should be left empty", Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(AddHouseActivity.this, "House Added Successfully...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddReportActivity.this, ReportToAuthorityActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }
            }

        });
    }

    public void uploadImage(View v) {
        Intent getImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getImageIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(getImageIntent, "Select an Image"), UPLOAD_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO: Implement this method
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case UPLOAD_IMAGE_REQUEST:
                    imageUri = data.getData();
                    try {
                        Bitmap imgBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imageValue = bitmapToByteArray(imgBitmap);
                        showSelectedImage(imgBitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showSelectedImage(Bitmap imgBitmap) {
        // TODO: Implement this method
        selectedReportImageView.setImageBitmap(imgBitmap);
        selectedReportImageView.setVisibility(View.VISIBLE);
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public void onBackPressed() {
        // TODO: Implement this method
        super.onBackPressed();
        Intent gotoReportActivity = new Intent(AddReportActivity.this, ReportToAuthorityActivity.class);
        startActivity(gotoReportActivity);
        finishAffinity();
    }

}
