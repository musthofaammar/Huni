package id.eureka.benchmark

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

    private fun startup(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "id.eureka.hunicompose",
        metrics = listOf(StartupTimingMetric()),
        compilationMode = compilationMode,
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }

    private fun allScreen(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "id.eureka.hunicompose",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        compilationMode = compilationMode,
        startupMode = StartupMode.COLD
    ) {
        getStartedHome()
        scrollAndGoToDetail()
        goToVirtualTourAndScroll()
    }

    @Test
    fun startupCompilationNone() = startup(CompilationMode.None())

    @Test
    fun startupCompilationPartial() = startup(CompilationMode.Partial())

    @Test
    fun startupCompilationFull() = startup(CompilationMode.Full())

    @Test
    fun allScreenCompilationNone() = allScreen(CompilationMode.None())

    @Test
    fun allScreenCompilationPartial() = allScreen(CompilationMode.Partial())
}

fun MacrobenchmarkScope.getStartedHome() {
    pressHome()
    startActivityAndWait()

    device.wait(Until.hasObject(By.text("Get Started")), 2000)

    device.findObject(By.res("button_get_started"))?.click()
}

fun MacrobenchmarkScope.scrollAndGoToDetail() {
    device.wait(Until.hasObject(By.res("content_list")), 2000)

    device.waitForIdle()

    device.findObject(By.res("content_list")).also {
        it.setGestureMargin(device.displayHeight / 5)
        it.fling(Direction.DOWN)
    }

    device.wait(Until.hasObject(By.text("Hotel Autumn Center 5")), 5000)

    device.findObject(By.text("Hotel Autumn Center 5"))?.click()
    device.wait(Until.hasObject(By.text("Griya Asri Cempaka Raya")), 5000)
}

fun MacrobenchmarkScope.goToVirtualTourAndScroll() {
    device.findObject(By.res("virtual_tour_button"))?.click()

    device.wait(Until.hasObject(By.res("virtual_room_list")), 5000)

    device.findObject(By.res("virtual_room_list"))?.apply {
        setGestureMargin(device.displayWidth / 5)
        swipe(Direction.LEFT, 1f)
        device.waitForIdle()
    }
}