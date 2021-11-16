package com.example.mogawelogintoken.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.mogawelogintoken.databinding.FragmentLoginBinding
import com.example.mogawelogintoken.mvvm.LoginViewModel
import com.example.mogawelogintoken.restapi.model.LogInSubmit
import com.example.mogawelogintoken.restapi.response.ApiResponse
import com.example.mogawelogintoken.value.sha256
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    //mvvm
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitLogin()
    }

    private fun submitLogin() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()
            Timber.d("chek email : $email")

            val logInSubmit = LogInSubmit(
                email, password.sha256()
            )
            initiateLogInUser(logInSubmit)
        }
    }

    //memulai login user
    private fun initiateLogInUser(submit: LogInSubmit) {
        viewModel.logIn(submit).observe(viewLifecycleOwner, Observer { logIn ->
            when (logIn) {
                is ApiResponse.Loading -> {
                    showLoading(true)
                }
                is ApiResponse.Success -> {
                    //jika succes akan di arahakn masuk dan mmbawa data token
                    showLoading(false)
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToUserFragment(logIn.data.token)
                    view?.findNavController()?.navigate(action)
                    Timber.d("berhasil masuk : ${logIn.data.token}")
                }
                is ApiResponse.Error -> {
                    showLoading(false)
                    //jika error akan menampilkan messeg error dari api
                    showErrorDialog(logIn.errorMessege)
                    Timber.d("Error: {${logIn.errorMessege}}")
                }
                else -> {
                    Timber.d("Unknow Error")
                }
            }
        })
    }

    //menampilkan loading
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.bgDim.visibility = View.VISIBLE
            binding.progressBar.visibility = View.VISIBLE
            binding.edEmail.isEnabled = false
            binding.edPassword.isEnabled = false
        } else {
            binding.bgDim.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.edEmail.isEnabled = true
            binding.edPassword.isEnabled = true
        }
    }

    //menampilkan error
    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("ada kesalahan")
            setMessage(message)
            setPositiveButton("OK") { p0, _ ->
                p0.dismiss()
            }
        }.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}