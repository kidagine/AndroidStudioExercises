package com.example.swordzlist.Infrastructure;

import com.example.swordzlist.Entities.Sword;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {

    private static FakeDatabase fakeDatabase;
    private List<Sword> swords = new ArrayList<>();


    public static FakeDatabase instance()
    {
        if (fakeDatabase == null){
            fakeDatabase = new FakeDatabase();
            fakeDatabase.initializeData();
        }
        return fakeDatabase;
    }

    private void initializeData(){
        swords.add(new Sword(1, "Rapier", 69.99));
        swords.add(new Sword(2,"Katana", 299.99));
        swords.add(new Sword(3,"Claymore", 299.99));
        swords.add(new Sword(4,"NotASword", 53.99));
        swords.add(new Sword(5,"Long Sword", 82.99));
        swords.add(new Sword(6,"Flamberge", 299.99));
        swords.add(new Sword(7,"ShietSword", 99.99));
        swords.add(new Sword(8,"GreatSword", 199.99));
        swords.add(new Sword(9,"Brave Sword", 899.99));
        swords.add(new Sword(10,"Bastard Sword", 89.99));
        swords.add(new Sword(11,"Big Ass Club", 399.99));
        swords.add(new Sword(12,"Swooooooordz", 999.99));
        swords.add(new Sword(13,"Straight Sword", 54.99));
        swords.add(new Sword(14,"Literally A Gun", 225.99));
        swords.add(new Sword(15,"A Random Grandma", 249.99));
        swords.add(new Sword(16,"Black Knight Sword", 89.99));
        swords.add(new Sword(17,"Moonlight GreatSword", 299.99));
        swords.add(new Sword(18,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(19,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(20,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(21,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(22,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(23,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(24,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(25,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(26,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(27,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(28,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(29,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(30,"Two bananas and a pogo stick", 999.99));
        swords.add(new Sword(31,"Two bananas and a pogo stick", 999.99));
    }

    public List<Sword> getSwords(){
        return swords;
    }
}
