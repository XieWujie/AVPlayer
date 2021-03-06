package com.example.main.viewmodel


import androidx.lifecycle.ViewModel
import com.example.main.MainActivity
import com.example.main.repository.IMainRepository
import com.dibus.ViewModelService

class MainViewModel @ViewModelService(MainActivity::class) constructor(val repository:IMainRepository):ViewModel()