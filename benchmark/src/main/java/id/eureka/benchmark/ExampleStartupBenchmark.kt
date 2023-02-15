package id.eureka.benchmark

import android.bluetooth.BluetoothClass.Device
import androidx.benchmark.macro.*
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "id.eureka.hunicompose",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun goToHome() = benchmarkRule.measureRepeated(
        packageName = "id.eureka.hunicompose",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
        allScreen()
    }
}

fun MacrobenchmarkScope.allScreen() {

    device.wait(Until.hasObject(By.text("Get Started")), 2000)

    device.findObject(By.res("button_get_started"))?.click()

    device.wait(Until.hasObject(By.res("nearby_list")), 2000)

    device.findObject(By.res("nearby_list")).apply {
        setGestureMargin(device.displayWidth / 5)
        repeat(4){
            swipe(Direction.LEFT, 1f)
            device.waitForIdle()
        }
    }

    device.wait(Until.hasObject(By.text("Hotel Autumn Center 5")), 5000)

    device.findObject(By.text("Hotel Autumn Center 5"))?.click()

    device.wait(Until.hasObject(By.text("Griya Asri Cempaka Raya")), 5000)

    device.findObject(By.res("virtual_tour_button"))?.click()

    device.wait(Until.hasObject(By.res("virtual_room_list")), 5000)

    device.findObject(By.res("virtual_room_list"))?.apply {
        setGestureMargin(device.displayWidth / 5)
        swipe(Direction.LEFT, 1f)
    }
}