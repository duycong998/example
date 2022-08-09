package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main_coroutine.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivityCoroutine : AppCompatActivity() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    var isChecked = false
    var value = 1
    var list = mutableListOf<Person>()
    val adapter by lazy {}

    object A {
        fun sum(a: Int, b: Int): Int {
            return a + b
        }
    }

    var x = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_coroutine)
        lifecycleScope.launch {
            while (isActive) {
                show()
                x++
                delay(1000L)
                if (x > 6) {
                    x = 1
                }
                Log.d("AAAAccc", x.toString())
            }
        }



        A.sum(1, 5)

        disposable.add(
            getTimeStamp().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<Long>() {
                    override fun onNext(t: Long) {
                        if (value == 5) {
                            isChecked = true
                        }
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onComplete() {
                        TODO("Not yet implemented")
                    }

                })
        )

        disposable.add(
            get().subscribeOn(Schedulers.io()).subscribe()

        )


//        Log.d("AAAACCC", A.sum(5 , 1).toString())
//       lifecycleScope.launch {
//            Log.d("AAAAA", "Starting")
//            delay(1000L)
//            Log.d("AAAAA", "Processing")
//            delay(2000L)
//            Log.d("AAAAA", "Done")
//        }

//        Log.d("AAAAA", "Starting")
//        val handler = Handler()
//        handler.postDelayed(
//            {
//                Log.d("AAAAA", "Processing")
//                handler.postDelayed({
//                    Log.d("AAAAA", "Done")
//                }, 2000L)
//            }, 1000L
//        )

    }

    fun show() {
        when (x) {
            1 -> {
                image_viewOne.visibility = View.VISIBLE
                image_viewFour.visibility = View.GONE
                image_viewTwo.visibility = View.GONE
                image_viewThree.visibility = View.GONE
                image_viewFive.visibility = View.GONE
                image_viewSix.visibility = View.GONE
                image_viewOne.setImageResource(R.drawable.ic_launcher_background)
            }
            2 -> {
                image_viewTwo.visibility = View.VISIBLE
                image_viewOne.visibility = View.GONE
                image_viewFour.visibility = View.GONE
                image_viewThree.visibility = View.GONE
                image_viewFive.visibility = View.GONE
                image_viewSix.visibility = View.GONE
                image_viewTwo.setImageResource(R.drawable.ic_launcher_background)
            }
            3 -> {
                image_viewThree.visibility = View.VISIBLE
                image_viewOne.visibility = View.GONE
                image_viewTwo.visibility = View.GONE
                image_viewFour.visibility = View.GONE
                image_viewFive.visibility = View.GONE
                image_viewSix.visibility = View.GONE
                image_viewThree.setImageResource(R.drawable.ic_launcher_background)
            }
            4 -> {
                image_viewFour.visibility = View.VISIBLE
                image_viewOne.visibility = View.GONE
                image_viewTwo.visibility = View.GONE
                image_viewThree.visibility = View.GONE
                image_viewFive.visibility = View.GONE
                image_viewSix.visibility = View.GONE
                image_viewFour.setImageResource(R.drawable.ic_launcher_background)
            }
            5 -> {
                image_viewFive.visibility = View.VISIBLE
                image_viewOne.visibility = View.GONE
                image_viewTwo.visibility = View.GONE
                image_viewThree.visibility = View.GONE
                image_viewFour.visibility = View.GONE
                image_viewSix.visibility = View.GONE
                Log.d("AAAAfive", "CCCCCCCCCCCCCC")
                image_viewFive.setImageResource(R.drawable.ic_launcher_background)
            }
            6 -> {
                image_viewSix.visibility = View.VISIBLE
                image_viewOne.visibility = View.GONE
                image_viewTwo.visibility = View.GONE
                image_viewThree.visibility = View.GONE
                image_viewFour.visibility = View.GONE
                image_viewFive.visibility = View.GONE
                image_viewSix.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }

    private fun get(): Observable<Long> {
        return Observable.interval(5, TimeUnit.SECONDS)
            .filter { l -> isChecked }
            .doOnNext { Log.d("AAA", "CCCCokk") }
    }


    private fun getTimeStamp(): Observable<Long> {
        return Observable.interval(15, TimeUnit.SECONDS)
            .doOnNext {
                value++
                Log.d("AAA", value.toString())
            }
    }
}