package com.example.listviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"Apple","Banana","Orange","Watermelon",
            "Pear","Grape","Pineapple","Strawberry","Cherry", "Mango",
            "Apple","Banana","Orange","Watermelon",
            "Pear","Grape","Pineapple","Strawberry","Cherry", "Mango"};

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, data);
                //数组的数据（data数组的数据）是无法直接传递给ListView的，需要借助适配器（adapter）来完成
                //ArrayAdapter:适配器的实现类，通过“泛型”来指定要适配的数据类型，然后在其构造函数中把要适配的数据传进去
                //simple_list_item_1：作为ListView子项布局的id，这是一个Android内置的布局文件，里面只有一个TextView可用于简单的显示一段文本
        ListView listView = (ListView)findViewById(R.id.list_view);
        //调用setAdapter方法把适配器对象传递进去
        listView.setAdapter(adapter);*/

        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,
                R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        /*将FruitAdapter类型的adapter对象作为适配器（adpater）传递给ListView*/
        listView.setAdapter(adapter);

        this.registerForContextMenu(listView);


        /*使用setOnItemClickListener方法为ListView注册了一个监听器，
        当用户点击ListView的任何一个子项的时候，就会回调onItemClick方法，使用Toast显示该水果名称*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,
                        "Should I eat this "+fruit.getName() + "？",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.test_menu,menu);

        /*获取Item位置*/
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String name = fruitList.get(adapterContextMenuInfo.position).getName();
        menu.setHeaderTitle(name);
        menu.setHeaderIcon(R.drawable.ic_launcher_background);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.First:{
                Toast.makeText(this,"点击了分享然后额。。。不太会了",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.Second:{
                Toast.makeText(this,"图片已经保存本地相册",Toast.LENGTH_SHORT).show();
                break;
            }
            default:
                break;
        }
        return true;
    }



    /*初始化所有水果的数据*/
    private void initFruits(){
        for(int i = 0; i < 2; i++){
            /*生成两遍各种水果的对象（一遍不足以覆盖整个屏幕）*/

            Fruit apple = new Fruit("Apple",R.drawable.apple_pic);
            fruitList.add(apple);

            Fruit banana = new Fruit("Banana",R.drawable.banana_pic);
            fruitList.add(banana);

            Fruit orange = new Fruit("Orange",R.drawable.orange_pic);
            fruitList.add(orange);

            Fruit watermelon = new Fruit("Watermelon",R.drawable.watermelon_pic);
            fruitList.add(watermelon);

            Fruit pear = new Fruit("Pear",R.drawable.pear_pic);
            fruitList.add(pear);

            Fruit grape = new Fruit("grape",R.drawable.grape_pic);
            fruitList.add(grape);

            Fruit pineapple = new Fruit("Pineapple",R.drawable.pineapple_pic);
            fruitList.add(pineapple);

            Fruit strawberry = new Fruit("Strawberry",R.drawable.strawberry_pic);
            fruitList.add(strawberry);

            Fruit cherry = new Fruit("Cherry",R.drawable.cherry_pic);
            fruitList.add(cherry);

            Fruit mango = new Fruit("Mango",R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }


}