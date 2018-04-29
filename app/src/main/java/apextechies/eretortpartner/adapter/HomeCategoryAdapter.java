package apextechies.eretortpartner.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import apextechies.eretortpartner.R;
import apextechies.eretortpartner.intrface.OnClickListener;
import apextechies.eretortpartner.model.SchoolListDataModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shankar on 04/20/2018.
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder> {

    private Context context;
    private OnClickListener clickPosition;
    private ArrayList<SchoolListDataModel> list;
    private int item_row;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_gurukul)
        TextView tv_gurukul;
        @BindView(R.id.tv_location)
        TextView tv_location;
        @BindView(R.id.cv_main)
        CardView cv_main;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public HomeCategoryAdapter(Context context, ArrayList<SchoolListDataModel> list, int item_row, OnClickListener clickPosition) {
        this.context = context;
        this.list = list;
        this.item_row = item_row;
        this.clickPosition = clickPosition;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(item_row,
                parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        SchoolListDataModel model = list.get(position);

        if (model.getStatus().equals("1")) {
            holder.tv_gurukul.setText(model.getSchool_name());
            holder.tv_location.setText(model.getCity() + " (" + model.getDistrict() + ")");
        }
        else {
            holder.cv_main.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPosition.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

}

