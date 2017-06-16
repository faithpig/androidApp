package com.whu.faithfish.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whu.faithfish.androidapp.R;
import com.whu.faithfish.pojo.Fruit;

import java.util.List;

/**
 * Created by faithfish on 17-6-16.
 */

public class FruitAdapter extends ArrayAdapter<Fruit>{

    private int resourceId;

    public FruitAdapter(@NonNull Context context, @LayoutRes int resource, List<Fruit> objects){
        super(context, resource, objects);
        this.resourceId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImg = view.findViewById(R.id.fruit_image);
            viewHolder.textView = view.findViewById(R.id.fruit_text);
            //tag用于在view中携带数据
            view.setTag(R.id.tag_1,viewHolder);//为了保证id的唯一性，必须在ids.xml文件中定义此id

        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(R.id.tag_1);
        }
        viewHolder.fruitImg.setImageResource(fruit.getImg_id());
        viewHolder.textView.setText(fruit.getFruit_name());
        return view;
    }

    class ViewHolder{
        ImageView fruitImg;
        TextView textView;
    }
}
