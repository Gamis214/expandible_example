package gamis214.com.expandableexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.HashMap;
import java.util.List;

public class CustomAdapterMain extends RecyclerView.Adapter<CustomAdapterMain.ItemHolder> {

    private static final int UNSELECTED = -1;

    private RecyclerView recyclerView;
    private HashMap<String, List<String>> lstMap;
    private Context context;
    private int selectedItem = UNSELECTED;

    public CustomAdapterMain(RecyclerView recyclerView, Context context, HashMap<String, List<String>> lstMap) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.lstMap = lstMap;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        itemHolder.bind();
    }

    @Override
    public int getItemCount() {
        return lstMap.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
        private ExpandableLayout expandableLayout;
        private RelativeLayout container_expandable;
        private ImageView img;
        private TextView expandButton;
        private RecyclerView subRecyclerView;

        public ItemHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            expandButton = itemView.findViewById(R.id.expand_button);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            subRecyclerView = itemView.findViewById(R.id.subRecyclerView);
            container_expandable = itemView.findViewById(R.id.container_expandable);

            expandableLayout.setInterpolator(new OvershootInterpolator());
            expandableLayout.setOnExpansionUpdateListener(this);

            subRecyclerView.setLayoutManager(new LinearLayoutManager(expandButton.getContext()));
            DividerItemDecoration itemDecoration = new DividerItemDecoration(expandButton.getContext(),DividerItemDecoration.VERTICAL);
            subRecyclerView.addItemDecoration(itemDecoration);

            container_expandable.setOnClickListener(this);
        }

        public void bind() {

            Object[] keys = lstMap.keySet().toArray();
            List<String> lstValues = lstMap.get(keys[getAdapterPosition()]);

            int position = getAdapterPosition();
            boolean isSelected = position == selectedItem;

            expandButton.setText((String)keys[getAdapterPosition()]);
            expandButton.setSelected(isSelected);
            expandableLayout.setExpanded(isSelected, false);

            subRecyclerView.setAdapter(new CustomAdapterSub(position,lstValues));
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.container_expandable:
                    ItemHolder holder = (ItemHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
                    if (holder != null) {
                        holder.expandButton.setSelected(false);
                        holder.expandableLayout.collapse();
                        img.setImageResource(R.drawable.ic_plus);
                    }

                    int position = getAdapterPosition();
                    if (position == selectedItem) {
                        selectedItem = UNSELECTED;
                    } else {
                        expandButton.setSelected(true);
                        expandableLayout.expand();
                        img.setImageResource(R.drawable.ic_minus);
                        selectedItem = position;
                    }
                    break;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            if (state == ExpandableLayout.State.EXPANDING) {
                recyclerView.smoothScrollToPosition(getAdapterPosition());
            }
        }
    }
}
