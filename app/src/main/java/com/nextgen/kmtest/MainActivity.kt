package com.nextgen.kmtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.nextgen.kmtest.databinding.ActivityMainBinding
import com.nextgen.kmtest.ui.firstscreen.FirstFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val firstFragment = FirstFragment()
        val mFragmentManager = supportFragmentManager
        val mFragment = mFragmentManager.findFragmentByTag(FirstFragment::class.java.simpleName)
        if (mFragment !is FirstFragment){
            mFragmentManager.commit {
                add(
                    R.id.container,
                    firstFragment,
                    FirstFragment::class.java.simpleName
                )
            }
        }



    }


}