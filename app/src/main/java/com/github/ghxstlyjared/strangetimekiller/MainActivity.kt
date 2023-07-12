package com.github.ghxstlyjared.strangetimekiller

import android.app.ProgressDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.ghxstlyjared.strangetimekiller.contracts.MainActivityContract
import com.github.ghxstlyjared.strangetimekiller.contracts.MainActivityContract.Presenter
import com.github.ghxstlyjared.strangetimekiller.data.responses.RandomDogResponse
import com.github.ghxstlyjared.strangetimekiller.databinding.ActivityMainBinding
import com.github.ghxstlyjared.strangetimekiller.mvp.presenters.MainViewPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


class MainActivity : AppCompatActivity(), MainActivityContract.View {
    var mPresenter: Presenter? = null
    var progressDialog: ProgressDialog? = null
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mPresenter = MainViewPresenter(this)

        initListeners()
    }

    private fun initListeners() {
        mBinding.buttonRefresh.setOnClickListener {
            mPresenter?.getDog(getString(R.string.api_key))
        }
    }

    //region ----- View Contract Methods -----
    override fun setupUI() {
        progressDialog = ProgressDialog(this)
    }

    override val aPIKey: String
        get() = getString(R.string.api_key)

    override fun displayDogData(dogExample: RandomDogResponse?) {
        CoroutineScope(Dispatchers.Main).launch {
            val url = dogExample?.urls?.get(0) ?: "https://images.dog.ceo/breeds/airedale/n02096051_8227.jpg"
            val bmp = withContext(Dispatchers.IO) {
                val inputStream = URL(url).openStream()
                BitmapFactory.decodeStream(inputStream)
            }
            mBinding.dogPicture.setImageBitmap(bmp)
            Log.d("mvpTEST", dogExample.toString())
        }

    }

    override fun showMessage(msg: String?) {
        Log.d("mvpTEST", msg.toString())
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.setMessage("Loading...")
        } else {
            progressDialog!!.isIndeterminate = true
            progressDialog!!.setMessage("Loading...")
            progressDialog!!.setCancelable(false)
            try {
                progressDialog!!.show()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    override fun hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
                progressDialog!!.hide()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    } //endregion
}