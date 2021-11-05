package online.danbao.studentinfomanager2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import online.danbao.studentinfomanager2.R;
import online.danbao.studentinfomanager2.activity.EditActivity;

public class SearchAdapter extends RecyclerView.Adapter<online.danbao.studentinfomanager2.adapter.SearchAdapter.ViewHolder> {
    private Context mContext;

    private ArrayList<String> mNumber;
    private ArrayList<String> mName;

    public final static int maxSize = 50;//最多显示50条

    public SearchAdapter(Context context, ArrayList<String> number, ArrayList<String> name) {
        mContext = context;
        mNumber = number;
        mName = name;
    }

    @Override
    public online.danbao.studentinfomanager2.adapter.SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_search, parent, false);

        return new ViewHolder(views);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llSearch;
        public TextView tvNumber;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            llSearch = (LinearLayout) itemView.findViewById(R.id.ll_search);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_search_number);
            tvName = (TextView) itemView.findViewById(R.id.tv_search_name);
        }

    }

    @Override
    public void onBindViewHolder(online.danbao.studentinfomanager2.adapter.SearchAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.tvNumber.setText(mNumber.get(position));
        holder.tvName.setText(mName.get(position));

        holder.llSearch.setOnClickListener(new View.OnClickListener() {
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
