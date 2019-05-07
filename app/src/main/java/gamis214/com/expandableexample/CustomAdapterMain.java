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
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

public class CustomAdapterMain extends RecyclerView.Adapter<CustomAdapterMain.ItemHolder> {

    private static final int UNSELECTED = -1;

    private RecyclerView recyclerView;
    private Context context;
    private int selectedItem = UNSELECTED;

    public CustomAdapterMain(RecyclerView recyclerView, Context context) {
        this.recyclerView = recyclerView;
        this.context = context;
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
        return 100;
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
        private ExpandableLayout expandableLayout;
        private TextView expandButton;
        private RecyclerView subRecyclerView;

        public ItemHolder(View itemView) {
            super(itemView);
            expandButton = itemView.findViewById(R.id.expand_button);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            subRecyclerView = itemView.findViewById(R.id.subRecyclerView);

            expandableLayout.setInterpolator(new OvershootInterpolator());
            expandableLayout.setOnExpansionUpdateListener(this);

            subRecyclerView.setLayoutManager(new LinearLayoutManager(expandButton.getContext()));
            DividerItemDecoration itemDecoration = new DividerItemDecoration(expandButton.getContext(),DividerItemDecoration.VERTICAL);
            subRecyclerView.addItemDecoration(itemDecoration);

            expandButton.setOnClickListener(this);
        }

        public void bind() {
            int position = getAdapterPosition();
            boolean isSelected = position == selectedItem;

            expandButton.setText(position + ". Tap to expand");
            expandButton.setSelected(isSelected);
            expandableLayout.setExpanded(isSelected, false);

            subRecyclerView.setAdapter(new CustomAdapterSub(position));
        }

        @Override
        public void onClick(View view) {
            ItemHolder holder = (ItemHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.expandButton.setSelected(false);
                holder.expandableLayout.collapse();
            }

            int position = getAdapterPosition();
            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                expandButton.setSelected(true);
                expandableLayout.expand();
                selectedItem = position;
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
