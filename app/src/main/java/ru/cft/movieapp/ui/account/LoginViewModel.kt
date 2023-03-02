package ru.cft.movieapp.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import ru.cft.movieapp.data.firebase.FirebaseUser

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED,
        UNAUTHENTICATED,
        INVALID_AUTHENTICATION
    }
    val authenticationState = FirebaseUser().map { user ->
        if(user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}