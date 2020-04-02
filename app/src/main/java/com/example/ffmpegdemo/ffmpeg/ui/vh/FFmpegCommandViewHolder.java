package com.example.ffmpegdemo.ffmpeg.ui.vh;

import android.view.View;
import android.widget.Button;


import com.example.ffmpegdemo.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: AnJoiner
 * @datetime: 19-12-30
 */
public class FFmpegCommandViewHolder extends RecyclerView.ViewHolder {

    public Button mButton;
    public FFmpegCommandViewHolder(@NonNull View itemView) {
        super(itemView);
        mButton = itemView.findViewById(R.id.btn_transform_audio);
    }
}
