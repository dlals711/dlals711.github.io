package code.codesample.memo.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import code.codesample.memo.Memo;
import code.codesample.memo.R;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ItemViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position, Memo memo);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, attachToRoot:false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Memo memo = data.get(position);
        holder.textViewTitle.setText(memo.title);
        holder.textViewTime.setText(memo.time);
        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(position, memo);
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewTime;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.edtittext_title);
            textViewTime = itemView.findViewById(R.id.textview_time);
        }
    }

    private List<Memo> data;
    private OnItemClickListener listener;

    public MemoAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Memo> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
