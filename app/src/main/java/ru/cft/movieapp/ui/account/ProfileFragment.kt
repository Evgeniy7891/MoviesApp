package ru.cft.movieapp.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentProfileBinding
import ru.cft.movieapp.util.AuthenticationState

class ProfileFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Cannot access view")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthState()
    }

    private fun observeAuthState() {
        viewModel.authenticationState.observe(viewLifecycleOwner) { authState ->
            if (authState == AuthenticationState.AUTHENTICATED) {
                val hello = String.format(
                    resources.getString(
                        R.string.welcome_message,
                        FirebaseAuth.getInstance().currentUser?.displayName
                    )
                )
                binding.tvWelcome.text = hello
                binding.btnLogout.setOnClickListener {
                    AuthUI.getInstance().signOut(requireContext())
                    findNavController().popBackStack()
                }
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}