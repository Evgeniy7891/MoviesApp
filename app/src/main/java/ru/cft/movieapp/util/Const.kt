package ru.cft.movieapp.util

import ru.cft.movieapp.data.room.repository.MoviesRoomRepositoryImpl
import ru.cft.movieapp.ui.MainActivity

lateinit var MAIN: MainActivity
lateinit var REALISATION: MoviesRoomRepositoryImpl
const val TAG = "LoginFragment"
const val SIGN_IN_RESULT_CODE = 1001
const val WAITING_FOR_DATA : Int = 1000