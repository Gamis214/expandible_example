package gamis214.com.expandableexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomAdapterSub extends RecyclerView.Adapter<CustomAdapterSub.SubViewHolder>{

    private int headerPosition;
    private List<String> lstValues;

    public CustomAdapterSub(int headerPosition,List<String> lstValues){
        this.headerPosition = headerPosition;
        this.lstValues = lstValues;
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sub,viewGroup,false);
        return new SubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, int i) {
        subViewHolder.bind();
    }

    @Override
    public int getItemCount() {
        return lstValues.size();
    }

    public class SubViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSub;
        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSub = itemView.findViewById(R.id.txtSub);
        }
        public void bind(){
            txtSub.setText(""+lstValues.get(getAdapterPosition()));
        }
    }

}
