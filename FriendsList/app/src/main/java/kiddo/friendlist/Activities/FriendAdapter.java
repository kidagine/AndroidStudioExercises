package kiddo.friendlist.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kiddo.friendlist.Entities.Friend;
import kiddo.friendlist.R;

public class FriendAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Activity context;
    private ArrayList<Friend> friends;

    public FriendAdapter(Activity context, ArrayList<Friend> friends) {
        this.context = context;
        this.friends = friends;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View friendView = convertView;
        friendView = (friendView == null) ? inflater.inflate(R.layout.friend_view, null): friendView;
        TextView txvName = friendView.findViewById(R.id.txvName);
        TextView txvEmail = friendView.findViewById(R.id.txvEmail);
        ImageView imvProfilePicture = friendView.findViewById(R.id.imvProfilePicture);
        Friend selectedFriend = friends.get(position);

        byte[] profilePictureByteArray = selectedFriend.getProfilePicture();
        Bitmap profilePicture = BitmapFactory.decodeByteArray(profilePictureByteArray, 0, profilePictureByteArray.length);
        imvProfilePicture.setImageBitmap(profilePicture);

        txvName.setText(selectedFriend.getName());
        txvEmail.setText(selectedFriend.getPhoneNumber());

        return friendView;
    }
}
