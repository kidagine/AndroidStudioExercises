package kiddo.friendlist.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;

import kiddo.friendlist.Entities.Friend;
import kiddo.friendlist.R;

public class DetailActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;

    private ImageButton ibnProfilePicture;
    private EditText dtxName;
    private EditText dtxEmail;
    private EditText dtxPhoneNumber;
    private Friend friendItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeElements();
        setFriendInformation();
    }

    private void initializeElements() {
        ibnProfilePicture = findViewById(R.id.ibnProfilePicture);
        dtxName = findViewById(R.id.dtxName);
        dtxEmail = findViewById(R.id.dtxEmail);
        dtxPhoneNumber = findViewById(R.id.dtxPhoneNumber);


        Button btnSave = findViewById(R.id.btnSave);
        Button btnCall = findViewById(R.id.btnCall);
        Button btnMessage = findViewById(R.id.btnMessage);
        Button btnEmail = findViewById(R.id.btnEmail);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFriendInformation();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCall();
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        ibnProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProfilePicture();
            }
        });
    }

    private void saveFriendInformation() {
        Bitmap profilePicture = ((BitmapDrawable) ibnProfilePicture.getBackground()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        profilePicture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] profilePictureByteArray = byteArrayOutputStream.toByteArray();

        friendItem.setProfilePicture(profilePictureByteArray);
        friendItem.setName(dtxName.getText().toString());
        friendItem.setEmail(dtxEmail.getText().toString());
        friendItem.setPhoneNumber(dtxPhoneNumber.getText().toString());

        Intent detailIntent = new Intent();
        detailIntent.putExtra("friend", friendItem);
        setResult(RESULT_OK, detailIntent);

        finish();
    }

    private void sendCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + dtxPhoneNumber.getText().toString()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        }
    }

    private void sendMessage() {
        Intent messageIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("smsto", dtxPhoneNumber.getText().toString(), null));
        startActivity(messageIntent);
    }

    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", dtxEmail.getText().toString(), null));
        startActivity(Intent.createChooser(emailIntent, "Send email"));
    }

    private void changeProfilePicture() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            BitmapDrawable profilePictureDrawable = new BitmapDrawable(getResources(), photo);
            ibnProfilePicture.setBackground(profilePictureDrawable);
        }
    }

    private void setFriendInformation() {
        friendItem = (Friend) getIntent().getSerializableExtra("friend");

        byte[] profilePictureByteArray = friendItem.getProfilePicture();
        Bitmap profilePicture = BitmapFactory.decodeByteArray(profilePictureByteArray, 0, profilePictureByteArray.length);
        BitmapDrawable profilePictureDrawable = new BitmapDrawable(getResources(), profilePicture);
        ibnProfilePicture.setBackground(profilePictureDrawable);

        dtxName.setText(friendItem.getName());
        dtxEmail.setText(friendItem.getEmail());
        dtxPhoneNumber.setText(friendItem.getPhoneNumber());
    }
}
