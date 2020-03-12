package kiddo.friendlist.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import kiddo.friendlist.Entities.Friend;
import kiddo.friendlist.Infrastructure.FakeDatabase;
import kiddo.friendlist.R;

public class MainActivity extends AppCompatActivity {

    private static final int DETAIL_ACTIVITY_REQUEST_CODE = 0;
    private ListView lstFriends;
    private ArrayList<Friend> friends = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElements();
        loadFriendsList();
    }

    private void initializeElements() {
        lstFriends = findViewById(R.id.lstFriends);
        lstFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSelectedFriend(parent, position);
            }
        });
    }

    private void openSelectedFriend(AdapterView<?> parent, int position) {
        Friend friend = (Friend)parent.getItemAtPosition(position);
        Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
        detailIntent.putExtra("friend", friend);
        startActivityForResult(detailIntent, DETAIL_ACTIVITY_REQUEST_CODE);
    }

    private void loadFriendsList() {
        friends = new ArrayList<>(FakeDatabase.instance(this).getFriends());
        FriendAdapter friendAdapter = new FriendAdapter(this, friends);
        lstFriends.setAdapter(friendAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAIL_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                updateFriendInformation(data);
            }
        }
    }

    private void updateFriendInformation(Intent data){
        Friend friendItem = (Friend) data.getSerializableExtra("friend");
        if (friendItem != null){
            for(Friend friend : friends){
                if (friend.getId() == friendItem.getId()) {
                    friend.setProfilePicture(friendItem.getProfilePicture());
                    friend.setName(friendItem.getName());
                    friend.setEmail(friendItem.getEmail());
                    friend.setPhoneNumber(friendItem.getPhoneNumber());
                    loadFriendsList();
                }
            }
        }
    }
}
