package com.ideasApp.listofideas.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ideasApp.listofideas.R

class IdeaItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val tvName: TextView = view.findViewById(R.id.tv_name)
    val tvDescription: TextView = view.findViewById(R.id.tv_description)
}
