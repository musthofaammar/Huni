package id.eureka.hunicompose.virtualtour

import id.eureka.hunicompose.R
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.panoramagl.PLImage
import com.panoramagl.PLManager
import com.panoramagl.PLSphericalPanorama
import com.panoramagl.utils.PLUtils
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.databinding.ActivityVirtualTourBinding


class VirtualTourActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVirtualTourBinding
    private lateinit var plManager : PLManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVirtualTourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigator()

        plManager = PLManager(this).apply {
            setContentView(binding.root)
            onCreate()
        }

        val panorama = PLSphericalPanorama()
        panorama.camera.lookAt(30.0f, 90.0f)

        panorama.setImage(PLImage(PLUtils.getBitmap(this, R.drawable.image360_3), false))
        plManager.panorama = panorama
    }

    private fun setNavigator() {
        binding.virtualTourNavigator.setContent {
            VirtualTourNavigator()
        }
    }

    override fun onResume() {
        super.onResume()
        plManager.onResume()
    }

    override fun onPause() {
        plManager.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        plManager.onDestroy()
        super.onDestroy()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return plManager.onTouchEvent(event!!)
    }


}