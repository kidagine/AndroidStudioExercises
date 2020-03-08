package com.example.swordzlist.Activities;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swordzlist.Entities.Sword;
import com.example.swordzlist.Infrastructure.FakeDatabase;
import com.example.swordzlist.R;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    ArrayList<Sword> swordItems = new ArrayList<>();
    SwordAdapter swordAdapter;

    private static final int DETAILS_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupSwordList();
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id){
        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra("sword", swordItems.get(position));
        startActivityForResult(detailsIntent, DETAILS_ACTIVITY_REQUEST_CODE);
    }

    private void setupSwordList(){
        swordItems = new ArrayList<>(FakeDatabase.instance().getSwords());
        swordAdapter = new SwordAdapter(this, swordItems);
        this.setListAdapter(swordAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAILS_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                setSwordInformation(data);
            }
        }
    }

    private void setSwordInformation(Intent data){
        Sword swordItem = (Sword) data.getSerializableExtra("sword");
        if (swordItem!= null){
            for(Sword sword : swordItems){
                if (sword.getId() == swordItem.getId()){
                    Toast.makeText(this, swordItem.getName(), Toast.LENGTH_LONG).show();
                    sword.setName(swordItem.getName());
                    sword.setPrice(swordItem.getPrice());
                }
            }
        }
    }
}

class SwordAdapter extends ArrayAdapter<Sword> {

    private ArrayList<Sword> swords;

    public SwordAdapter(Context context, ArrayList<Sword> swords) {
        super(context, 0, swords);
        this.swords = swords;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if (v == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.activity_main, null);
        }

        Sword sword = swords.get(position);
        TextView name = v.findViewById(R.id.name);
        TextView phone = v.findViewById(R.id.price);

        name.setText(sword.getName());
        phone.setText(sword.getPrice() + "$");
        return v;
    }
}
