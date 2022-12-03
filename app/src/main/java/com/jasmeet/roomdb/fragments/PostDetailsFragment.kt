package com.jasmeet.roomdb.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasmeet.roomdb.R
import com.jasmeet.roomdb.databinding.FragmentPostDetailsBinding
import com.jasmeet.roomdb.models.PostsItem


class PostDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentPostDetailsBinding
    private val binding get() = _binding
    private val item : PostDetailsFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostDetailsBinding.inflate(layoutInflater)
        val data:PostsItem = item.post

        addToWatchlist(data)

        val id = PostDetailsFragmentArgs.fromBundle(requireArguments()).post.id
        val userId = PostDetailsFragmentArgs.fromBundle(requireArguments()).post.userId
        val title = PostDetailsFragmentArgs.fromBundle(requireArguments()).post.title
        val body = PostDetailsFragmentArgs.fromBundle(requireArguments()).post.body

        binding.tvBody.text = body
        binding.tvId.text = id.toString()
        binding.tvTitle.text = title
        binding.tvUserId.text = userId.toString()

        return binding.root

    }
    var watchList : ArrayList<String> ?= null
    var watchListIsChecked = false


    private fun addToWatchlist(data: PostsItem) {
        readData()

        watchListIsChecked = if (watchList!!.contains(data.body)){
            binding.fav.setImageResource(R.drawable.star)
            true
        }else{
            binding.fav.setImageResource(R.drawable.star_outline)
            false
        }
        binding.fav.setOnClickListener {
            watchListIsChecked =
                if (!watchListIsChecked){
                    if(!watchList!!.contains(data.body)){
                        watchList!!.add(data.body)
                    }
                    binding.fav.setImageResource(R.drawable.star)
                    true
                }else{
                    if(watchList!!.contains(data.body)){
                        watchList!!.remove(data.body)
                    }
                    binding.fav.setImageResource(R.drawable.star_outline)
                    false
                }
            storeData()
        }
    }

    private fun storeData(){
        val sharedPreferences = requireContext().getSharedPreferences("watchlist", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(watchList)
        editor.putString("watchlist",json)
        editor.apply()

    }

    private fun readData() {
        val sharedPreferences = requireContext().getSharedPreferences("watchlist", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("watchlist",ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>(){}.type

        watchList = gson.fromJson(json,type)

    }


}

