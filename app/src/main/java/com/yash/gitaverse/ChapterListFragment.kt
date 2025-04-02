package com.yash.gitaverse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yash.gitaverse.adapter.ChapterAdapter
import com.yash.gitaverse.databinding.FragmentChapterListBinding
import com.yash.gitaverse.model.Chapter
import com.yash.gitaverse.network.RetrofitInstance
import com.yash.gitaverse.repository.ChapterRepository
import com.yash.gitaverse.utils.NetworkResult
import com.yash.gitaverse.viewmodel.ChapterViewModel
import com.yash.gitaverse.viewmodel.ChapterViewModelFactory

class ChapterListFragment : Fragment() {

    private lateinit var binding: FragmentChapterListBinding
    private val viewModel: ChapterViewModel by viewModels {
        ChapterViewModelFactory(ChapterRepository(RetrofitInstance.api))
    }
    private lateinit var adapter: ChapterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChapterListBinding.inflate(layoutInflater, container, false)
        adapter = ChapterAdapter() {
            onChapterClicked(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        viewModel.chapters.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Error<*> -> {
                    MaterialAlertDialogBuilder(requireActivity()).setTitle("Error Occurred")
                        .setMessage(it.message).setPositiveButton("GOT IT") { _, _ -> }.show()
                }

                is NetworkResult.Loading<*> -> {
                    Toast.makeText(requireContext(), "Loading....", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Success<*> -> {
                    adapter.submitList(it.data)
                }
            }
        }


    }

    private fun onChapterClicked(chapter: Chapter) {
        findNavController().navigate(R.id.action_chapterListFragment_to_chapterDetailFragment)

    }


}