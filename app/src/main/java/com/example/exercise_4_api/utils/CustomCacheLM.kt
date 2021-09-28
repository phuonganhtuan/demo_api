package com.example.exercise_4_api.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomCacheLM(context: Context?) : LinearLayoutManager(context) {
    override fun getExtraLayoutSpace(state: RecyclerView.State?) = 5000
}
