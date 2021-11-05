package online.danbao.studentinfomanager2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import online.danbao.studentinfomanager2.R;
import online.danbao.studentinfomanager2.activity.EditActivity;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {
    private Context mContext;

    private ArrayList<String> mNumber;
    private ArrayList<String> mName;
    private ArrayList<String> mGender;

    public final static int maxSize = 50;

    public ShowAdapter(Context context,
                       ArrayList<String> number, ArrayList<String> name, ArrayList<String> gender) {
        mContext = context;
        mNumber = number;
        mName = name;
        mGender = gender;
    }

    @Override
    public ShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_show, parent, false);

        return new ViewHolder(views);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvShow;
        public TextView tvNumber;
        public TextView tvName;
        public TextView tvGender;

        public ViewHolder(View itemView) {
            super(itemView);
            cvShow = (CardView) itemView.findViewById(R.id.cv_show);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_show_number);
            tvName = (TextView) itemView.findViewById(R.id.tv_show_name);
            tvGender = (TextView) itemView.findViewById(R.id.tv_show_gender);
        }

    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(ShowAdapter.ViewHolder holder, final int position) {

        holder.tvNumber.setText(mNumber.get(position));
        holder.tvName.setText(mName.get(position));
        holder.tvGender.setText(mGender.get(position));

        holder.cvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("type", EditActivity.TYPE_EDIT);
                intent.putExtra("number", mNumber.get(position));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mNumber.size() < maxSize)
            return mNumber.size();
        else
            return maxSize;
    }


}
