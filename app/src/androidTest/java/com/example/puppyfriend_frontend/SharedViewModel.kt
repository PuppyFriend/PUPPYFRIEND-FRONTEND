package com.example.puppyfriend_frontend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.puppyfriend_frontend.View.Sns.model.Posting

class SharedViewModel : ViewModel() {
    private val _postings = MutableLiveData<List<Posting>>()
    val postings: LiveData<List<Posting>> get() = _postings

    fun setPostings(postings: List<Posting>) {
        _postings.value = postings
    }
}
