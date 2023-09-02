package com.example.puppyfriend_frontend.View.Walk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyfriend_frontend.databinding.ItemWalkNeighborBinding

class WalkNeighborAdapter:RecyclerView.Adapter<PagerViewHolder>(){
    var listData= mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding=ItemWalkNeighborBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val memo=listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}
class PagerViewHolder(val binding:ItemWalkNeighborBinding):RecyclerView.ViewHolder(binding.root){
    fun setMemo(memo:String){
        binding.txtName.text="${memo}"
    }
}