package ru.cft.movieapp.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentAccountBinding

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthState()
    }

    private fun observeAuthState() {
        viewModel.authenticationState.observe(viewLifecycleOwner, { authState ->
            when(authState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    binding.btnPersonalAccount.setOnClickListener {
                        findNavController().navigate(R.id.profileFragment)
                    }
                }
                else -> {
                    binding.btnPersonalAccount.setOnClickListener {
                        findNavController().navigate(R.id.loginFragment)
                    }
                }
            }
        })
    }
}