package com.example.myaddflutterapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.myaddflutterapp.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class FlutterModuleActivity  : FlutterActivity() {

    companion object {
        // main.dartでMethodChannelのコンストラクタで指定した文字列です
        private const val CHANNEL = "iwatake.core_module.flutter_core_module"
        // main.dartでinvokeMethodの第一引数に指定したmethodの文字列です
        private const val METHOD_TEST = "test"
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        // 起動するページの設定
        //flutterEngine.navigationChannel.setInitialRoute("/your_initial_route")
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        // MethodChannelからのメッセージを受け取ります
        // （flutterViewはFlutterActivityのプロパティ、CHANNELはcompanion objectで定義しています）
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
            .setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
                if (methodCall.method == METHOD_TEST) {
                    // invokeMethodの第二引数で指定したパラメータを取得できます
                    val parameters = methodCall.arguments<String>()
                    launchAndroidScreen(parameters)
                }
            }
    }

    private fun launchAndroidScreen(parameters: String?) {
        val intent = Intent(context,MainSubActivity::class.java)
        startActivity(intent)
    }
}
