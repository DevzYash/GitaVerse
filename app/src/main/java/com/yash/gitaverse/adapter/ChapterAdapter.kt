package com.yash.gitaverse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yash.gitaverse.databinding.ItemChapterBinding
import com.yash.gitaverse.model.Chapter

class ChapterAdapter(
    private val onItemClicked: (Chapter) -> Unit
) : ListAdapter<Chapter, ChapterAdapter.MyViewHolder>(DiffUtils()) {

    inner class MyViewHolder(private val binding: ItemChapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chapter: Chapter) {
            binding.chapterText.text =
                "Chapter ${chapter.chapter_number} ${chapter.name_transliterated}"
            binding.totalVerses.text = "${chapter.verses_count} Verses"
            binding.chapterNumber.text = chapter.chapter_number.toString()
            binding.chapterCard.setOnClickListener {
                onItemClicked(chapter)
            }
        }
    }

    class DiffUtils : DiffUtil.ItemCallback<Chapter>() {
        override fun areItemsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemChapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val chapter = getItem(position)
        holder.bind(chapter)
    }
}