package dejana.stevanovic.navapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<FinanceOffice> officeModelList;
    private View.OnClickListener clickListener;

    public RecyclerViewAdapter(List<FinanceOffice> officeModelList, View.OnClickListener clickListener) {
        this.officeModelList = officeModelList;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.office_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        FinanceOffice officeModel = officeModelList.get(position);
        String title = officeModel.getName();
        String subtitle = officeModel.getZip() + " " +  officeModel.getCity() + ", " + officeModel.getStreet();
        holder.txtTitle.setText(title);
        holder.txtSubtitle.setText(subtitle);
        holder.itemView.setTag(officeModel);
        holder.itemView.setOnClickListener(clickListener);
        if(position == getItemCount()-1)
            holder.divider.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return officeModelList.size();
    }

    public void addItems(List<FinanceOffice> officeModelList) {
        this.officeModelList = officeModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private TextView txtSubtitle;
        private View itemView;
        private View divider;

        RecyclerViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.titleTV);
            txtSubtitle = view.findViewById(R.id.subtitleTV);
            itemView = view.findViewById(R.id.itemView);
            divider = view.findViewById(R.id.divider);
        }
    }
}
