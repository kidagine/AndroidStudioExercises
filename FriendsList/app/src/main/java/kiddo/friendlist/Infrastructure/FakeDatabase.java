package kiddo.friendlist.Infrastructure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import kiddo.friendlist.Entities.Friend;
import kiddo.friendlist.R;

public class FakeDatabase {

    private static FakeDatabase fakeDatabase;
    private static int friendId;
    private List<Friend> friends = new ArrayList<>();
    public static FakeDatabase instance(Context context) {
        if (fakeDatabase == null){
            fakeDatabase = new FakeDatabase();
            fakeDatabase.initializeData(context);
        }
        return fakeDatabase;
    }

    private void initializeData(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap defaultProfilePicture = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_profile_picture);
        defaultProfilePicture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] defaultProfilePictureByteArray = byteArrayOutputStream.toByteArray();

        friends.add(new Friend(friendId++, "Mario Mario", "SuperMario@gmail.com", "24+ 82756499", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Olgilvie Maurice Hedgehog", "JoelMiller@gmail.com", "58+ 91655421", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Samus Aran", "Motherbrain@gmail.com", "61+ 76659211", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Solid Snake", "Snaaaaake@gmail.com", "23+ 33768238", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Gordon Freeman", "Crowbar@gmail.com", "64+ 75447482", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Master Chief", "Cortana@gmail.com", "77+ 82010152", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Cloud Strife", "BusterSword@gmail.com", "43+ 92828842", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Siegmeyer Catarina", "OnionBro@gmail.com", "28+ 66573888", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Joel Miller", "BabyGirl@gmail.com", "45+ 42929470", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Diddy Kong", "Bananas@gmail.com", "02+ 02040846", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Handsome Jack", "Hyperion@gmail.com", "31+ 74885521", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Geralt Rivia", "WhiteWolf@gmail.com", "92+ 82020245", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Ryu Hayabusa", "Geiden@gmail.com", "64+ 85624692", defaultProfilePictureByteArray));
        friends.add(new Friend(friendId++, "Fox McCloud", "BarrelRoll@gmail.com", "55+ 03736354", defaultProfilePictureByteArray));
    }

    public List<Friend> getFriends() {
        return friends;
    }
}
