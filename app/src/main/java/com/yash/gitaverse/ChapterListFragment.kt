package com.yash.gitaverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yash.gitaverse.adapter.ChapterAdapter
import com.yash.gitaverse.databinding.FragmentChapterListBinding
import com.yash.gitaverse.network.RetrofitInstance
import com.yash.gitaverse.repository.ChapterRepository
import com.yash.gitaverse.viewmodel.ChapterViewModel
import com.yash.gitaverse.viewmodel.ChapterViewModelFactory

class ChapterListFragment : Fragment() {

    private lateinit var binding: FragmentChapterListBinding
    private val viewModel: ChapterViewModel by viewModels {
        ChapterViewModelFactory(ChapterRepository(RetrofitInstance.api))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChapterListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.chapters.observe(viewLifecycleOwner) { chapters ->
            val adapter = ChapterAdapter(chapters)
            binding.recyclerView.adapter = adapter
        }

        viewModel.fetchChapters()


    }


}