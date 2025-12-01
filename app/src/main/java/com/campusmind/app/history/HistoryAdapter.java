package com.campusmind.app.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.campusmind.app.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<SessionHistoryModel> historyList;

    public HistoryAdapter(List<SessionHistoryModel> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        SessionHistoryModel item = historyList.get(position);
        holder.tvSessionType.setText("Tipe: " + item.getSessionType());
        holder.tvSessionTime.setText("Waktu: " + item.getTime());
        holder.tvSessionLastMessage.setText(item.getLastMessage());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvSessionType, tvSessionTime, tvSessionLastMessage;
        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSessionType = itemView.findViewById(R.id.tv_session_type);
            tvSessionTime = itemView.findViewById(R.id.tv_session_time);
            tvSessionLastMessage = itemView.findViewById(R.id.tv_session_last_message);
        }
    }
}
