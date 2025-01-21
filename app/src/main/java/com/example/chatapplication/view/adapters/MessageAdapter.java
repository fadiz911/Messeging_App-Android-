package com.example.chatapplication.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.R;
import com.example.chatapplication.services.model.Chats;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MSG_TYPE_LEFT_RECEIVED = 0; // Received text message
    private static final int MSG_TYPE_RIGHT_SENT = 1;    // Sent text message
    private static final int MSG_TYPE_FILE_LEFT_RECEIVED = 2; // Received file message
    private static final int MSG_TYPE_FILE_RIGHT_SENT = 3;    // Sent file message

    private ArrayList<Chats> chatArrayList;
    private Context context;
    private String currentUser_sender;

    public MessageAdapter(ArrayList<Chats> chatArrayList, Context context, String currentUser_sender) {
        this.chatArrayList = chatArrayList;
        this.context = context;
        this.currentUser_sender = currentUser_sender;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT_SENT) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right_sent, parent, false);
            return new TextMessageHolder(view);
        } else if (viewType == MSG_TYPE_LEFT_RECEIVED) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left_received, parent, false);
            return new TextMessageHolder(view);
        } else if (viewType == MSG_TYPE_FILE_RIGHT_SENT) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right_sent, parent, false);
            return new FileMessageHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left_received, parent, false);
            return new FileMessageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chats chat = chatArrayList.get(position);
        String timeStamp = chat.getTimestamp();
        boolean isSeen = chat.isSeen();
        long intTimeStamp = Long.parseLong(timeStamp);
        String time_msg_received = timeStampConversionToTime(intTimeStamp);

        if (holder.getItemViewType() == MSG_TYPE_FILE_RIGHT_SENT || holder.getItemViewType() == MSG_TYPE_FILE_LEFT_RECEIVED) {
            // Bind file message data
            FileMessageHolder fileHolder = (FileMessageHolder) holder;
            fileHolder.tvFileName.setText(chat.getFileName());
            fileHolder.tvFileSize.setText(chat.getFileSize());
            fileHolder.tvTime.setText(time_msg_received);

            if (position == chatArrayList.size() - 1) {
                fileHolder.tvSeen.setVisibility(View.VISIBLE);
                fileHolder.tvSeen.setText(isSeen ? "Seen" : "Delivered");
            } else {
                fileHolder.tvSeen.setVisibility(View.GONE);
            }
        } else {
            // Bind text message data
            TextMessageHolder textHolder = (TextMessageHolder) holder;
            textHolder.tvMsg.setText(chat.getMessage());
            textHolder.tvTime.setText(time_msg_received);

            if (position == chatArrayList.size() - 1) {
                textHolder.tvSeen.setVisibility(View.VISIBLE);
                textHolder.tvSeen.setText(isSeen ? "Seen" : "Delivered");
            } else {
                textHolder.tvSeen.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Chats chat = chatArrayList.get(position);
        if (chat.isFile()) {
            // File message
            if (chat.getSenderId().equals(currentUser_sender)) {
                return MSG_TYPE_FILE_RIGHT_SENT; // Sent file message
            } else {
                return MSG_TYPE_FILE_LEFT_RECEIVED; // Received file message
            }
        } else {
            // Text message
            if (chat.getSenderId().equals(currentUser_sender)) {
                return MSG_TYPE_RIGHT_SENT; // Sent text message
            } else {
                return MSG_TYPE_LEFT_RECEIVED; // Received text message
            }
        }
    }

    // ViewHolder for text messages
    public static class TextMessageHolder extends RecyclerView.ViewHolder {
        TextView tvMsg, tvTime, tvSeen;

        public TextMessageHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tv_chat_received);
            tvTime = itemView.findViewById(R.id.tv_chat_time_received);
            tvSeen = itemView.findViewById(R.id.tv_seen);
        }
    }

    // ViewHolder for file messages
    public static class FileMessageHolder extends RecyclerView.ViewHolder {
        TextView tvFileName, tvFileSize, tvTime, tvSeen;
        ImageView ivFileIcon;

        public FileMessageHolder(@NonNull View itemView) {
            super(itemView);
            tvFileName = itemView.findViewById(R.id.tv_file_name);
            tvFileSize = itemView.findViewById(R.id.tv_file_size);
            tvTime = itemView.findViewById(R.id.tv_chat_time_received);
            tvSeen = itemView.findViewById(R.id.tv_seen);
            ivFileIcon = itemView.findViewById(R.id.iv_file_icon);
        }
    }

    // Helper method to convert timestamp to time
    @SuppressLint("SimpleDateFormat")
    public String timeStampConversionToTime(long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat jdf = new SimpleDateFormat("hh:mm a");
        jdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        return jdf.format(date);
    }
}