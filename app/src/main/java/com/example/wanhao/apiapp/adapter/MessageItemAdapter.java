package com.example.wanhao.apiapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanhao.apiapp.R;
import com.example.wanhao.apiapp.bean.Message;

import java.util.ArrayList;
import java.util.List;

import static com.example.wanhao.apiapp.config.Constant.IMAGE_MESSAGE;
import static com.example.wanhao.apiapp.config.Constant.NORMAL_MESSAGE;


/**
 * Created by wanhao on 2017/11/26.
 */

public class MessageItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "MessageItemAdapter";

    private List<Message> list;
    private OnItemClickListener mOnItemClickListener = null;
    View view;
    Context context;

    public MessageItemAdapter(Context context){
        list = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<Message> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType==IMAGE_MESSAGE){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_img, viewGroup, false);
            HolderImage vh = new HolderImage(view);
            view.setOnClickListener(this);
            return vh;
        }
        if(viewType==NORMAL_MESSAGE){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_normal, viewGroup, false);
            HolderNormal vh = new HolderNormal(view);
            view.setOnClickListener(this);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HolderImage){
            Message message =list.get(position);

            ((HolderImage)holder).title.setText(message.getTitle());
            ((HolderImage)holder).description.setText(message.getDescription());
            ((HolderImage)holder).time.setText(message.getTime());
            ((HolderImage)holder).image.setImageBitmap(message.getImage());
            //Glide.with(context).load(message.getImage()).into(((HolderImage)holder).image);
        }
        if(holder instanceof HolderNormal){
            Message message =list.get(position);

            ((HolderNormal)holder).title.setText(message.getTitle());
            ((HolderNormal)holder).description.setText(message.getDescription());
            ((HolderNormal)holder).time.setText(message.getTime());
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemViewType (int position) {
        return list.get(position).getType();
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class HolderImage extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;
        public TextView description;
        public TextView time;

        public HolderImage(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_message_title);
            description = (TextView) view.findViewById(R.id.item_message_user);
            time = (TextView) view.findViewById(R.id.item_message_date);
            image = (ImageView) view.findViewById(R.id.item_message_image);
        }
    }
    public static class HolderNormal extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public TextView time;

        public HolderNormal(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_message_normal_title);
            description = (TextView) view.findViewById(R.id.item_message_normal_user);
            time = (TextView) view.findViewById(R.id.item_message_normal_date);
        }
    }
}