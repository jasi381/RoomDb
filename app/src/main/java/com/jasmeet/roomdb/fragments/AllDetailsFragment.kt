package com.jasmeet.roomdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jasmeet.roomdb.R
import com.jasmeet.roomdb.adapters.PostAdapter
import com.jasmeet.roomdb.api.RetrofitService
import com.jasmeet.roomdb.databinding.FragmentAllDetailsBinding
import com.jasmeet.roomdb.models.PostsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentAllDetailsBinding
    private val binding get() = _binding
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllDetailsBinding.inflate(layoutInflater)

        getPosts()
        return binding.root
    }

    private fun getPosts() {

        val posts = RetrofitService.retrofitInstance.getPost()

        posts.enqueue(object : Callback<List<PostsItem>?> {
            override fun onResponse(
                call: Call<List<PostsItem>?>,
                response: Response<List<PostsItem>?>
            ) {
                val postsList = response.body()
                binding.progressBar.visibility = View.GONE
                postAdapter = PostAdapter(requireContext(),postsList as ArrayList<PostsItem>)
                binding.rvAllPosts.adapter = postAdapter
            }

            override fun onFailure(call: Call<List<PostsItem>?>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(),t.message.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }

}