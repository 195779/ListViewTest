package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    /*继承自ArrayAdapter*/

    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*覆写了getView方法：这个方法是在子项被滚动到屏幕内的时候会被调用*/
        Fruit fruit = getItem(position);
        /*getItem方法：获取当前项的fruit实例*/
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            /*getView方法中的convertView参数用于将之前加载好的布局进行缓存，以便以后可以进行重用
            * 如果为空，我们就去调用LayoutInflater去加载布局，如果不为空则直接对convertView进行复用以提升运行效率*/
            view = LayoutInflater.from(getContext()).inflate(resourceId,
                    parent,false);
            /*使用LayoutInflater方法来为这个子项动态加载我们传入的布局：resourceId
            * 父布局：parent
            * false：表示只让我们在父布局中声明的layout属性生效，但不为这个view添加父布局
            * 因为一旦view有了父布局之后，它就不能再添加到ListView中了*/
            viewHolder = new ViewHolder();
            /*使用新增的内部类：viewHolder，对控件的实例进行缓存*/
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            /*使用setTag方法，把ViewHolder类型的实例"捆绑存储"在view上*/
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        /*
        //使用convertView参数之后，就不用这两句来每次都创建一遍控件实例了
        ImageView fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);*/

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder{
        /*内部类：用于对控件的实例进行缓存*/
        ImageView fruitImage;
        TextView fruitName;
    }
}
