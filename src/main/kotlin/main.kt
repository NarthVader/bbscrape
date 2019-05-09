package com.pinnsights

import org.openqa.selenium.*
import java.util.HashMap
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities


fun main(args: Array<String>) {
    scrape()
}

fun scrape() {
    val chromePrefs = HashMap<String, Any>()
    chromePrefs["profile.default_content_settings.popups"] = 0
    val options = ChromeOptions()
    options.setExperimentalOption("prefs", chromePrefs)
    val cap = DesiredCapabilities.chrome()
    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    cap.setCapability(ChromeOptions.CAPABILITY, options)
    val driver = ChromeDriver(cap)

    birdbreeders(driver)
}

fun birdbreeders(driver: ChromeDriver) {
    val bbUsr = "customersupport@thefinchfarm.com"
    val bbPw = "Twoeggdirectory2019!"

    try {
        driver.get("https://www.birdbreeders.com")
        driver.findElement(By.id("loginLink")).click()
        Thread.sleep(1000)
        driver.findElement(By.id("Email")).sendKeys("$bbUsr")
        driver.findElement(By.id("Password")).sendKeys("$bbPw")
        driver.findElement(By.xpath("//button[@class='btn btn-two pull-right']")).click()
        Thread.sleep(10000)
        breedersUpdate(driver)
    }
    catch (a: UnhandledAlertException) {
        println("Alert Exception: $a")
        driver.switchTo().alert().accept()
        breedersUpdate(driver)
    } catch (t: TimeoutException) {
        println("Timeout Exception: $t")
    } catch (n: NoSuchElementException) {
        println("No Such Element Exception: $n")
    } catch (o: Exception) {
        println("Other Exceptions: $o")
    }
}

fun breedersUpdate(driver: ChromeDriver) {

    Thread.sleep(3000)
    val breeders: IntArray = intArrayOf(176592,176505,176421,176419,173586,174934,176107,175394,173911,174632,174690,169895,175123,174089,
        175891,172898,175954,175958,173567,172820,174503,170715,176078,176075,173545,176204,174329,176041,176074,176299,176077,175351,176176,170837,175122,
        173952,173247,176300,173932,174591,174967,175650,174023,175089,176364,174622,175402,174496,175395,174106,173521,174506,176040,173492,174200,175972,
        176362,175974,176042,175586,175305,175957,173799,175536,175994,175539,175666,175367,175110,175426,174218,175212,175973,176039,174357,174401,
        171778,174348,176203,174402,175106,175718,171071,173663,176323,176277,176537,176470,176469,176590,176468,176520,176519,176571,176570,176569,176568)
    println("birdbreeders: " + breeders.size)

    for (i in 0..breeders.size-1) {
        val index = breeders[i]
        println("$i: " + index)
        driver.get("https://www.birdbreeders.com/myaccount/mybirds/edit/$index")
        Thread.sleep(3000)
        driver.findElement(By.xpath("//button[@class='btn btn-two pull-right']")).click()
        Thread.sleep(3000)
    }
    driver.quit()
}