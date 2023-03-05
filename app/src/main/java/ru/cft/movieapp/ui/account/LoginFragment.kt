package ru.cft.movieapp.ui.account

import ru.cft.movieapp.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import ru.cft.movieapp.databinding.FragmentLoginBinding
import ru.cft.movieapp.util.AuthenticationState
import ru.cft.movieapp.util.SIGN_IN_RESULT_CODE
import ru.cft.movieapp.util.TAG

class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var navController: NavController
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Cannot access view")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
        navController = findNavController()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.popBackStack(R.id.accountFragment, false)
        }
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authState ->
            when (authState) {
                AuthenticationState.AUTHENTICATED -> {
                    navController.popBackStack()
                    navController.navigate(R.id.profileFragment)
                }
                AuthenticationState.INVALID_AUTHENTICATION -> {
                    Snackbar.make(view, requireActivity().getString(R.string.login_unsuccessful_msg),
                    Snackbar.LENGTH_LONG
                    ).show()
                }
                else -> Log.d(TAG, "Authentication state that doesn't require any UI change $authState")
            }
        })
    }

    private fun initClick() {
        with(binding) {
            btnLogin.setOnClickListener {
                launchSignIn()
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun launchSignIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            )
                .setTheme(R.style.LoginTheme)
                .build(), SIGN_IN_RESULT_CODE
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                Log.d(
                    TAG,
                    "Successfully signed in user" + "${FirebaseAuth.getInstance().currentUser?.displayName}"
                )
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}