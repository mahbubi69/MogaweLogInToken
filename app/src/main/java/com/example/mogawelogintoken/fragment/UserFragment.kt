package com.example.mogawelogintoken.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mogawelogintoken.R
import com.example.mogawelogintoken.databinding.FragmentUserBinding
import com.example.mogawelogintoken.mvvm.UserViewModel
import com.example.mogawelogintoken.restapi.model.UserModel
import com.example.mogawelogintoken.restapi.response.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    //mvvm
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTokenUser()
    }

    fun getTokenUser() {
        val safeArgs = arguments?.let { UserFragmentArgs.fromBundle(it) }
        val token = safeArgs?.token ?: ""
        initiateUserDetail(token)
    }

    //memulai akun detail
    fun initiateUserDetail(token: String) {
        Timber.d("token is $token")
        viewModel.getDataUserDetail(token).observe(viewLifecycleOwner, Observer { user ->
            when (user) {
                is ApiResponse.Loading -> {
                    showLoading(true)
                }
                is ApiResponse.Success -> {
                    showLoading(false)
                    initiateViewUserData(user.data.objectUser)
                }
                is ApiResponse.Error -> {
                    showLoading(false)
                }
                else -> {
                    showLoading(false)
                }
            }
        })
    }

    //inisiasi data user
    //menyatukan apa yg di tampilkan dengan ui
    private fun initiateViewUserData(user: UserModel) {
        binding.imgProfil.setImageResource(if (user.gender == "M") R.drawable.ic_male else R.drawable.ic_famale)
        binding.tvFullNama.text = user.fullName
        Timber.d("nama : $user.fullName")
        binding.email.text = user.email
        binding.mogaweCode.text = user.mogawersCode
    }

    //menampilkan loading
    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.bgDim.visibility = View.VISIBLE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.bgDim.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}