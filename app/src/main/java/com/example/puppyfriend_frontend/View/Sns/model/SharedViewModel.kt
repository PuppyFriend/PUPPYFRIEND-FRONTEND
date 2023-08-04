package com.example.puppyfriend_frontend.View.Sns.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _postings = MutableLiveData<List<Posting>>()
    val postings: LiveData<List<Posting>> get() = _postings

    fun setPostings(postings: List<Posting>) {
        _postings.value = postings
    }

    fun addPosting(posting: Posting) {
        _postings.value?.let {
            val updatedList = it.toMutableList()
            updatedList.add(posting)
            _postings.value = updatedList
        }
    }
}
