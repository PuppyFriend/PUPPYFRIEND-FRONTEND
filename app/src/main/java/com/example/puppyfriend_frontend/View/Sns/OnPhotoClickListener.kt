package com.example.puppyfriend_frontend.View.Sns

import android.provider.ContactsContract.Contacts.Photo

interface OnPhotoClickListener {
    fun onPhotoClick(photo: Photo)
}