package azhar.testlayoutvisibility.nanosoft.usemyappashomescreenandroid.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import azhar.testlayoutvisibility.nanosoft.usemyappashomescreenandroid.R;

public class MainActivity extends AppCompatActivity {

    Context context;
    String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry",
            "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_demo);
        context = MainActivity.this;

    }


    public void exitFromApp() {
        final CharSequence[] items = {"NO", "YES"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit from app?");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        return;
                    case 1:
                        finish();

                        break;
                    default:
                        return;
                }
            }
        });
        builder.show();
        builder.create();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            exitFromApp();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private static final int IMAGE_CAPTURE = 102;
    public static final int CAMERA_PIC_REQUEST = 1;//firstly define this
    private static final int VIDEO_CAPTURE = 101;

    public void openOSApplication(View view) {
        int id = view.getId();
        if (id == R.id.tvCall) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_APP_CONTACTS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if (id == R.id.tvMail) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_APP_EMAIL);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if (id == R.id.tvInbox) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if (id == R.id.tvChat) {
        //    setContentView(R.layout.meeting_notice_fragment);

//            setContentView(R.layout.meeting_notice01);
            ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_checked, mobileArray);

            // listView.setAdapter(adapter);
        }
        if (id == R.id.tvLeave) {
            //   startActivity(new Intent(context, LeaveActivity.class));
            setContentView(R.layout.item_raw_offecial);
        }
        if (id == R.id.tvCar) {
            startActivity(new Intent(MainActivity.this, CarActivity.class));
        }
        if (id == R.id.tvSMS) {
            Uri sms_uri = Uri.parse("smsto:");
            Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
            sms_intent.putExtra("", "");
            startActivity(sms_intent);
        }
        if (id == R.id.tvCamera) {

//            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//            startActivityForResult(intent, VIDEO_CAPTURE);
            /*Intent photo= new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(photo, CAMERA_PIC_REQUEST);*/

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, IMAGE_CAPTURE);
        }

        if (id == R.id.tvLoginLogout) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));

        }
        if (id == R.id.tvProfile) {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));

        }
        if (id == R.id.tvHome) {
            setContentView(R.layout.header_text_input_layout);
        }
        if (id == R.id.tvMeetingNotice) {
            startActivity(new Intent(this,  MeetingNoticeActivity.class));

        } if (id == R.id.tvMeetingMinutes) {
          setContentView(R.layout.meeting_minute);

        }
    }

}
