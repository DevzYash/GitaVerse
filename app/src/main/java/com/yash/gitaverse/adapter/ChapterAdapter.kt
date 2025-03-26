package com.yash.gitaverse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yash.gitaverse.databinding.ItemChapterBinding
import com.yash.gitaverse.model.Chapter

class ChapterAdapter(private val chapters: List<Chapter>) :
    RecyclerView.Adapter<ChapterAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chapter: Chapter) {
            binding.chapterNumber.text = chapter.id.toString()
            binding.chapterName.text = chapter.name
            binding.chapterSummary.text = chapter.chapter_summary
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemChapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val chapter = chapters[position]
        holder.bind(chapter)
    }
}