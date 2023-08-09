package com.example.puppyfriend_frontend.View.Sns.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.puppyfriend_frontend.View.Sns.model.Posting

class PostingRepository {
    private val _postingData = MutableLiveData<Posting>()
    val postingData: LiveData<Posting>
        get() = _postingData

    fun setPostingData(data: Posting) {
        _postingData.value = data
    }
}