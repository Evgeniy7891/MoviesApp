package ru.cft.movieapp.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import ru.cft.movieapp.data.firebase.FirebaseUser
import ru.cft.movieapp.util.AuthenticationState

class LoginViewModel : ViewModel() {

    val authenticationState = FirebaseUser().map { user ->
        if(user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}